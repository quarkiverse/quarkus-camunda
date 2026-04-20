package io.quarkiverse.camunda.devservices;

import io.camunda.process.test.impl.containers.CamundaContainer;
import io.quarkus.devservices.common.ConfigureUtil;
import org.testcontainers.containers.Network;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import static io.quarkiverse.camunda.devservices.DevServiceProcessor.DEV_SERVICE_LABEL;

public class QuarkusCamundaContainer extends CamundaContainer {

    private final int fixedExposedPort;
    private final int fixedExposedRestPort;
    private final boolean useSharedNetwork;

    private String hostName = null;

    public QuarkusCamundaContainer(DockerImageName image, int fixedExposedPort, String serviceName,
                                   boolean useSharedNetwork, boolean test, int testDebugExportPort, boolean devDebugExporter,
                                   int debugExporterPort, int fixedExposedRestPort) {
        super(image);
        log.debugf("Camunda broker docker image %s", image);
        this.fixedExposedPort = fixedExposedPort;
        this.fixedExposedRestPort = fixedExposedRestPort;
        this.useSharedNetwork = useSharedNetwork;

        if (serviceName != null) {
            withLabel(DEV_SERVICE_LABEL, serviceName);
        }
        if (test) {
            // create random port
            withDebugExporter(testDebugExportPort);
        } else {
            if (devDebugExporter) {
                debugExporter(debugExporterPort);
            }
        }
    }

    public void debugExporter(final int port) {
        final int containerPort = HostPortForwarder.forwardHostPort(port, 5);
        var receiver = "http://host.testcontainers.internal:" + containerPort + "/q/zeebe/records";
        //noinspection resource
        withCopyToContainer(
                MountableFile.forClasspathResource("debug-exporter.jar"), "/tmp/debug-exporter.jar")
                .withEnv("CAMUNDA_BROKER_EXPORTERS_DEBUG_JARPATH", "/tmp/debug-exporter.jar")
                .withEnv(
                        "CAMUNDA_BROKER_EXPORTERS_DEBUG_CLASSNAME", "io.camunda.containers.exporter.DebugExporter")
                .withEnv(
                        "CAMUNDA_BROKER_EXPORTERS_DEBUG_ARGS_URL", receiver);
    }

    @Override
    protected void configure() {
        super.configure();

        if (useSharedNetwork) {
            hostName = ConfigureUtil.configureSharedNetwork(this, "zeebe");
            addExposedPort(DEFAULT_ZEEBE_REST_PORT);
            withEnv("CAMUNDA_BROKER_NETWORK_ADVERTISEDHOST", hostName);
            return;
        } else {
            withNetwork(Network.SHARED);
        }

        if (fixedExposedPort > 0) {
            addFixedExposedPort(fixedExposedPort, DEFAULT_ZEEBE_GRPC_PORT);
        } else {
            addExposedPort(DEFAULT_ZEEBE_GRPC_PORT);
        }
        if (fixedExposedRestPort > 0) {
            addFixedExposedPort(fixedExposedRestPort, DEFAULT_ZEEBE_REST_PORT);
        } else {
            addExposedPort(DEFAULT_ZEEBE_REST_PORT);
        }
    }

    public int getGrpcPort() {
        if (useSharedNetwork) {
            return DEFAULT_ZEEBE_GRPC_PORT;
        }
        if (fixedExposedPort > 0) {
            return fixedExposedPort;
        }
        return super.getFirstMappedPort();
    }

    public int getRestPort() {
        if (useSharedNetwork) {
            return DEFAULT_ZEEBE_REST_PORT;
        }
        if (fixedExposedPort > 0) {
            return fixedExposedRestPort;
        }
        return super.getFirstMappedPort();
    }

    public String getZeebeHost() {
        return useSharedNetwork ? hostName : super.getHost();
    }
}

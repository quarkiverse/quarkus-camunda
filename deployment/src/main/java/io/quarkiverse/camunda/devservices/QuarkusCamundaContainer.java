package io.quarkiverse.camunda.devservices;

import static io.quarkiverse.camunda.devservices.DevServiceProcessor.DEV_SERVICE_LABEL;

import org.jboss.logging.Logger;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import io.camunda.process.test.impl.containers.CamundaContainer;
import io.camunda.process.test.impl.runtime.ContainerRuntimePorts;
import io.quarkus.devservices.common.ConfigureUtil;

public class QuarkusCamundaContainer extends CamundaContainer {

    private final int fixedExposedPort;
    private final int fixedExposedRestPort;
    private final boolean useSharedNetwork;
    private static final Logger log = Logger.getLogger(QuarkusCamundaContainer.class);
    public static final int DEFAULT_CAMUNDA_REST_PORT = ContainerRuntimePorts.CAMUNDA_REST_API;
    public static final int DEFAULT_CAMUNDA_GRPC_PORT = ContainerRuntimePorts.CAMUNDA_GATEWAY_API;

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
        addExposedPort(port);
        var receiver = "http://host.testcontainers.internal:" + port + "/q/zeebe/records";
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
            hostName = ConfigureUtil.configureSharedNetwork(this, "camunda");
            addExposedPort(DEFAULT_CAMUNDA_REST_PORT);
            withEnv("CAMUNDA_BROKER_NETWORK_ADVERTISEDHOST", hostName);
            return;
        } else {
            withNetwork(Network.SHARED);
        }

        if (fixedExposedPort > 0) {
            addFixedExposedPort(fixedExposedPort, DEFAULT_CAMUNDA_GRPC_PORT);
        } else {
            addExposedPort(DEFAULT_CAMUNDA_GRPC_PORT);
        }
        if (fixedExposedRestPort > 0) {
            addFixedExposedPort(fixedExposedRestPort, DEFAULT_CAMUNDA_REST_PORT);
        } else {
            addExposedPort(DEFAULT_CAMUNDA_REST_PORT);
        }
    }

    void withDebugExporter(final int port) {
        addExposedPort(port);

        //noinspection resource
        withCopyToContainer(
                MountableFile.forClasspathResource("debug-exporter.jar"), "/tmp/debug-exporter.jar")
                .withEnv("ZEEBE_BROKER_EXPORTERS_DEBUG_JARPATH", "/tmp/debug-exporter.jar")
                .withEnv(
                        "ZEEBE_BROKER_EXPORTERS_DEBUG_CLASSNAME", "io.zeebe.containers.exporter.DebugExporter")
                .withEnv(
                        "ZEEBE_BROKER_EXPORTERS_DEBUG_ARGS_URL",
                        "http://" + GenericContainer.INTERNAL_HOST_HOSTNAME + ":" + port + "/records");

    }
}

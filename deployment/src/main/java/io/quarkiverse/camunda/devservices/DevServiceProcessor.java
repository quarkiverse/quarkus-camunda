package io.quarkiverse.camunda.devservices;

import static io.quarkiverse.camunda.Processor.FEATURE_NAME;
import static io.quarkus.runtime.LaunchMode.DEVELOPMENT;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.jboss.logging.Logger;
import org.testcontainers.containers.Network;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import io.camunda.client.CamundaClient;
import io.quarkiverse.camunda.DevServiceBuildTimeConfig;
import io.quarkus.deployment.IsProduction;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.CuratedApplicationShutdownBuildItem;
import io.quarkus.deployment.builditem.DevServicesResultBuildItem;
import io.quarkus.deployment.builditem.DevServicesResultBuildItem.RunningDevService;
import io.quarkus.deployment.builditem.DevServicesSharedNetworkBuildItem;
import io.quarkus.deployment.builditem.DockerStatusBuildItem;
import io.quarkus.deployment.builditem.LaunchModeBuildItem;
import io.quarkus.deployment.console.ConsoleInstalledBuildItem;
import io.quarkus.deployment.console.StartupLogCompressor;
import io.quarkus.deployment.logging.LoggingSetupBuildItem;
import io.quarkus.devservices.common.ConfigureUtil;
import io.quarkus.devservices.common.ContainerAddress;
import io.quarkus.devservices.common.ContainerLocator;
import io.quarkus.runtime.configuration.ConfigUtils;
import io.zeebe.containers.ZeebeContainer;
import io.zeebe.containers.ZeebePort;
import io.zeebe.containers.util.HostPortForwarder;

public class DevServiceProcessor {

    private static final String DEFAULT_CAMUNDA_CONTAINER_IMAGE = "camunda/camunda";

    private static final String DEFAULT_CAMUNDA_VERSION = CamundaClient.class.getPackage().getImplementationVersion();

    private static final DockerImageName CAMUNDA_IMAGE_NAME = DockerImageName.parse(DEFAULT_CAMUNDA_CONTAINER_IMAGE)
            .withTag(DEFAULT_CAMUNDA_VERSION);

    private static final Logger log = Logger.getLogger(DevServiceProcessor.class);
    static final String PROP_ZEEBE_GATEWAY_ADDRESS = "quarkus.camunda.client.broker.gateway-address";
    static final String PROP_ZEEBE_REST_ADDRESS = "quarkus.camunda.client.broker.rest-address";
    private static final String DEV_SERVICE_LABEL = "quarkus-dev-service-camunda";
    public static final int DEFAULT_ZEEBE_GRPC_PORT = ZeebePort.GATEWAY_GRPC.getPort();
    public static final int DEFAULT_ZEEBE_REST_PORT = ZeebePort.GATEWAY_REST.getPort();
    private static final ContainerLocator zeebeContainerLocator = new ContainerLocator(DEV_SERVICE_LABEL,
            DEFAULT_ZEEBE_GRPC_PORT);
    static volatile ZeebeRunningDevService devService;
    static volatile ZeebeDevServiceCfg cfg;
    static volatile boolean first = true;

    @BuildStep(onlyIfNot = IsProduction.class, onlyIf = {
            io.quarkus.deployment.dev.devservices.DevServicesConfig.Enabled.class })
    public DevServicesResultBuildItem startZeebeContainers(LaunchModeBuildItem launchMode,
            List<DevServicesSharedNetworkBuildItem> devServicesSharedNetworkBuildItem,
            DevServiceBuildTimeConfig buildTimeConfig,
            Optional<ConsoleInstalledBuildItem> consoleInstalledBuildItem,
            CuratedApplicationShutdownBuildItem closeBuildItem,
            DockerStatusBuildItem dockerStatusBuildItem,
            BuildProducer<DevServicesProviderBuildItem> startResultProducer,
            LoggingSetupBuildItem loggingSetupBuildItem,
            io.quarkus.deployment.dev.devservices.DevServicesConfig devServicesConfig) {

        ZeebeDevServiceCfg configuration = getConfiguration(buildTimeConfig);

        if (devService != null) {
            boolean shouldShutdownTheBroker = !configuration.equals(cfg);
            if (!shouldShutdownTheBroker) {
                return devService.toBuildItem();
            }
            stopZeebe();
            cfg = null;
        }

        StartupLogCompressor compressor = new StartupLogCompressor(
                (launchMode.isTest() ? "(test) " : "") + "Zeebe Dev Services Starting:",
                consoleInstalledBuildItem, loggingSetupBuildItem);
        try {
            devService = startZeebe(dockerStatusBuildItem, configuration, launchMode,
                    !devServicesSharedNetworkBuildItem.isEmpty(),
                    devServicesConfig.timeout());
            if (devService == null) {
                compressor.closeAndDumpCaptured();
            } else {
                compressor.close();
            }
        } catch (Throwable t) {
            compressor.closeAndDumpCaptured();
            throw new RuntimeException(t);
        }

        if (devService == null) {
            return null;
        }

        // Configure the watch dog
        if (first) {
            first = false;
            Runnable closeTask = () -> {
                if (devService != null) {
                    stopZeebe();
                }
                first = true;
                devService = null;
                cfg = null;
            };
            closeBuildItem.addCloseTask(closeTask, true);
        }
        cfg = configuration;

        if (devService.isOwner()) {
            String tmp = devService.getConfig().get(PROP_ZEEBE_GATEWAY_ADDRESS);
            log.infof("The zeebe broker is ready to accept connections on %s (http://%s)",
                    tmp, tmp);
        }

        return devService.toBuildItem();
    }

    public static class ZeebeRunningDevService extends RunningDevService {

        public ZeebeRunningDevService(String name, String containerId, Closeable closeable, Map<String, String> config,
                String zeebeInternalUrl) {
            super(name, containerId, closeable, config);
        }
    }

    private ZeebeRunningDevService startZeebe(DockerStatusBuildItem dockerStatusBuildItem,
            ZeebeDevServiceCfg config,
            LaunchModeBuildItem launchMode, boolean useSharedNetwork, Optional<Duration> timeout) {

        if (!config.devServicesEnabled) {
            // explicitly disabled
            log.debug("Not starting dev services for Zeebe as it has been disabled in the config");
            return null;
        }

        if (ConfigUtils.isPropertyPresent(PROP_ZEEBE_GATEWAY_ADDRESS)) {
            log.debug("Not starting dev services for Zeebe as '" + PROP_ZEEBE_GATEWAY_ADDRESS + "' have been provided");
            return null;
        }

        if (!dockerStatusBuildItem.isContainerRuntimeAvailable()) {
            log.warn(
                    "Docker isn't working, please configure the zeebe broker servers gateway property ("
                            + PROP_ZEEBE_GATEWAY_ADDRESS + ").");
            return null;
        }

        final Optional<ContainerAddress> maybeContainerAddress = zeebeContainerLocator.locateContainer(config.serviceName,
                config.shared,
                launchMode.getLaunchMode());

        // Starting the broker
        final Supplier<ZeebeRunningDevService> defaultZeebeBrokerSupplier = () -> {

            DockerImageName image = CAMUNDA_IMAGE_NAME;
            if (config.imageName != null) {
                image = DockerImageName.parse(config.imageName);
            }

            int testDebugExportPort = config.testDebugExportPort;
            if (launchMode.isTest() && config.testExporter) {
                if (config.testDebugExportPort == 0) {
                    try (ServerSocket serverSocket = new ServerSocket(0)) {
                        testDebugExportPort = serverSocket.getLocalPort();
                    } catch (IOException e) {
                        log.error("Port for debug exporter receiver is not available");
                    }
                }
            }

            QuarkusCamundaContainer container = new QuarkusCamundaContainer(
                    image,
                    config.fixedExposedPort,
                    launchMode.getLaunchMode() == DEVELOPMENT ? config.serviceName : null,
                    useSharedNetwork,
                    launchMode.isTest(),
                    testDebugExportPort,
                    config.devDebugExporter,
                    config.debugReceiverPort,
                    config.fixedExposedRestPort);
            timeout.ifPresent(container::withStartupTimeout);

            // enable test-container reuse
            if (config.reuse) {
                container.withReuse(true);
            }

            container.start();

            String gateway = String.format("%s:%d", container.getZeebeHost(), container.getGrpcPort());
            String baseUrl = String.format("http://%s:%d", container.getZeebeHost(), container.getRestPort());
            String zeebeInternalUrl = container.getInternalAddress(DEFAULT_ZEEBE_GRPC_PORT);
            String testClient = container.getExternalAddress(DEFAULT_ZEEBE_GRPC_PORT);
            String testClientRest = container.getExternalAddress(DEFAULT_ZEEBE_REST_PORT);

            return new ZeebeRunningDevService(FEATURE_NAME,
                    container.getContainerId(),
                    new ContainerShutdownCloseable(container, FEATURE_NAME),
                    configMap(gateway, baseUrl, launchMode.isTest(), testClient, testClientRest, testDebugExportPort,
                            config.testExporter),
                    zeebeInternalUrl);
        };

        return maybeContainerAddress
                .map(containerAddress -> new ZeebeRunningDevService(FEATURE_NAME,
                        containerAddress.getId(),
                        null, configMap(containerAddress.getUrl(), containerAddress.getUrl(), false, null, null, null, false),
                        null))
                .orElseGet(defaultZeebeBrokerSupplier);
    }

    private static Map<String, String> configMap(String gateway, String baseUrl, boolean test, String testClient,
            String testClientRest,
            Integer testDebugExportPort,
            boolean testExporter) {
        Map<String, String> config = new HashMap<>();
        config.put(PROP_ZEEBE_GATEWAY_ADDRESS, gateway);
        config.put(PROP_ZEEBE_REST_ADDRESS, baseUrl);
        if (test && testExporter) {
            if (testDebugExportPort != null) {
                config.put("quarkiverse.zeebe.devservices.test.receiver-port", "" + testDebugExportPort);
            }
            if (testClient != null) {
                config.put("quarkiverse.zeebe.devservices.test.gateway-address", testClient);
                config.put("quarkiverse.zeebe.devservices.test.rest-address", testClientRest);
            }
        }
        return config;
    }

    private void stopZeebe() {
        if (devService != null) {
            try {
                devService.close();
            } catch (Throwable e) {
                log.error("Failed to stop the Zeebe broker", e);
            } finally {
                devService = null;
            }
        }
    }

    private ZeebeDevServiceCfg getConfiguration(DevServiceBuildTimeConfig cfg) {
        DevServicesConfig devServicesConfig = cfg.devService();
        return new ZeebeDevServiceCfg(devServicesConfig);
    }

    private static final class ZeebeDevServiceCfg {
        private final boolean devServicesEnabled;
        private final String imageName;
        private final Integer fixedExposedPort;
        private final Integer fixedExposedRestPort;
        private final boolean shared;
        private final String serviceName;

        private final boolean testExporter;
        private final int testDebugExportPort;

        private final boolean devDebugExporter;

        private final int debugReceiverPort;

        private final boolean reuse;

        public ZeebeDevServiceCfg(DevServicesConfig config) {
            this.devServicesEnabled = config.enabled();
            this.imageName = config.imageName().orElse(null);
            this.fixedExposedPort = config.port().orElse(0);
            this.fixedExposedRestPort = config.restPort().orElse(0);
            this.shared = config.shared();
            this.serviceName = config.serviceName();
            this.testExporter = config.test().exporter();
            this.testDebugExportPort = config.test().receiverPort().orElse(0);
            this.devDebugExporter = config.devExporter().enabled();
            this.debugReceiverPort = getPort();
            this.reuse = config.reuse();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ZeebeDevServiceCfg that = (ZeebeDevServiceCfg) o;
            return devServicesEnabled == that.devServicesEnabled && Objects.equals(imageName, that.imageName)
                    && Objects.equals(fixedExposedPort, that.fixedExposedPort);
        }

        @Override
        public int hashCode() {
            return Objects.hash(devServicesEnabled, imageName, fixedExposedPort);
        }
    }

    private static int getPort() {
        Config config = ConfigProvider.getConfig();
        return config
                .getOptionalValue("quarkus.http.port", Integer.class)
                .orElse(8080);
    }
}

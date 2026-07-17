package io.quarkiverse.camunda.devservices;

import static io.quarkiverse.camunda.CamundaProcessor.FEATURE_NAME;
import static io.quarkiverse.camunda.testcontainer.CamundaContainerRuntimePorts.CAMUNDA_REST_API;
import static io.quarkus.runtime.LaunchMode.DEVELOPMENT;

import java.io.Closeable;
import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import org.jboss.logging.Logger;
import org.testcontainers.utility.DockerImageName;

import io.camunda.client.CamundaClient;
import io.quarkiverse.camunda.CamundaDevServiceBuildTimeConfig;
import io.quarkiverse.camunda.testcontainer.CamundaContainer;
import io.quarkus.deployment.IsProduction;
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
import io.quarkus.devservices.common.ContainerAddress;
import io.quarkus.devservices.common.ContainerLocator;
import io.quarkus.runtime.configuration.ConfigUtils;

public class CamundaDevServiceProcessor {

    private static final String DEFAULT_CAMUNDA_CONTAINER_IMAGE = "camunda/camunda";

    private static final String DEFAULT_CAMUNDA_VERSION = CamundaClient.class.getPackage().getImplementationVersion();

    private static final DockerImageName CAMUNDA_IMAGE_NAME = DockerImageName.parse(DEFAULT_CAMUNDA_CONTAINER_IMAGE)
            .withTag(DEFAULT_CAMUNDA_VERSION);

    private static final Logger log = Logger.getLogger(CamundaDevServiceProcessor.class);
    static final String PROP_CAMUNDA_GATEWAY_ADDRESS = "quarkus.camunda.client.broker.gateway-address";
    static final String PROP_CAMUNDA_REST_ADDRESS = "quarkus.camunda.client.broker.rest-address";
    public static final String DEV_SERVICE_LABEL = "quarkus-dev-service-camunda";
    private static final ContainerLocator camundaContainerLocator = new ContainerLocator(DEV_SERVICE_LABEL, CAMUNDA_REST_API);
    static volatile CamundaRunningDevService devService;
    static volatile CamundaDevServiceCfg runningConfiguration;
    static volatile boolean first = true;

    @BuildStep(onlyIfNot = IsProduction.class, onlyIf = {
            io.quarkus.deployment.dev.devservices.DevServicesConfig.Enabled.class })
    public DevServicesResultBuildItem startCamundaContainers(LaunchModeBuildItem launchMode,
            List<DevServicesSharedNetworkBuildItem> devServicesSharedNetworkBuildItem,
            CamundaDevServiceBuildTimeConfig buildTimeConfig,
            Optional<ConsoleInstalledBuildItem> consoleInstalledBuildItem,
            CuratedApplicationShutdownBuildItem closeBuildItem,
            DockerStatusBuildItem dockerStatusBuildItem,
            LoggingSetupBuildItem loggingSetupBuildItem,
            io.quarkus.deployment.dev.devservices.DevServicesConfig devServicesConfig) {

        CamundaDevServiceCfg configuration = getConfiguration(buildTimeConfig);

        if (devService != null) {
            boolean shouldShutdownTheBroker = !configuration.equals(runningConfiguration);
            if (!shouldShutdownTheBroker) {
                return devService.toBuildItem();
            }
            shutdownCamunda();
            runningConfiguration = null;
        }

        StartupLogCompressor compressor = new StartupLogCompressor(
                (launchMode.isTest() ? "(test) " : "") + "Camunda Dev Services Starting:",
                consoleInstalledBuildItem, loggingSetupBuildItem);
        try {
            boolean useSharedNetwork = DevServicesSharedNetworkBuildItem.isSharedNetworkRequired(devServicesConfig,
                    devServicesSharedNetworkBuildItem);
            devService = startCamunda(dockerStatusBuildItem, configuration, launchMode,
                    useSharedNetwork,
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
                    shutdownCamunda();
                }
                first = true;
                devService = null;
                runningConfiguration = null;
            };
            closeBuildItem.addCloseTask(closeTask, true);
        }
        runningConfiguration = configuration;

        if (devService.isOwner() && !ConfigUtils.isPropertyPresent(PROP_CAMUNDA_GATEWAY_ADDRESS)) {
            String tmp = devService.getConfig().get(PROP_CAMUNDA_GATEWAY_ADDRESS);
            log.infof("Camunda is ready to accept connections on %s",
                    tmp);
        }

        if (devService.isOwner() && !ConfigUtils.isPropertyPresent(PROP_CAMUNDA_REST_ADDRESS)) {
            String tmp = devService.getConfig().get(PROP_CAMUNDA_REST_ADDRESS);
            log.infof("Camunda is ready to accept connections on %s",
                    tmp);
        }

        return devService.toBuildItem();
    }

    public static class CamundaRunningDevService extends RunningDevService {

        public CamundaRunningDevService(String name, String containerId, Closeable closeable, Map<String, String> config) {
            super(name, containerId, closeable, config);
        }
    }

    private CamundaRunningDevService startCamunda(DockerStatusBuildItem dockerStatusBuildItem,
            CamundaDevServiceCfg config,
            LaunchModeBuildItem launchMode, boolean useSharedNetwork, Optional<Duration> timeout) {

        if (!config.devServicesEnabled) {
            // explicitly disabled
            log.debug("Not starting dev services for Camunda as it has been disabled in the config");
            return null;
        }

        if (ConfigUtils.isPropertyPresent(PROP_CAMUNDA_GATEWAY_ADDRESS)) {
            log.debug("Not starting dev services for Camunda as '" + PROP_CAMUNDA_GATEWAY_ADDRESS + "' have been provided");
            return null;
        }

        if (ConfigUtils.isPropertyPresent(PROP_CAMUNDA_REST_ADDRESS)) {
            log.debug("Not starting dev services for Camunda as '" + PROP_CAMUNDA_REST_ADDRESS + "' have been provided");
            return null;
        }

        if (!dockerStatusBuildItem.isContainerRuntimeAvailable()) {
            log.warn(
                    "Docker isn't working, please configure the Camunda broker servers gateway property ("
                            + PROP_CAMUNDA_GATEWAY_ADDRESS + " OR " + PROP_CAMUNDA_REST_ADDRESS + ").");
            return null;
        }

        final Optional<ContainerAddress> maybeContainerAddress = camundaContainerLocator.locateContainer(config.serviceName,
                config.shared,
                launchMode.getLaunchMode());

        // Starting the broker
        final Supplier<CamundaRunningDevService> defaultCamundaBrokerSupplier = () -> {

            DockerImageName image = CAMUNDA_IMAGE_NAME;
            if (config.imageName != null) {
                image = DockerImageName.parse(config.imageName);
            }

            CamundaContainer container = new CamundaContainer(
                    image,
                    launchMode.getLaunchMode() == DEVELOPMENT ? config.serviceName : null,
                    useSharedNetwork);
            timeout.ifPresent(container::withStartupTimeout);

            // enable test-container reuse
            if (config.reuse) {
                container.withReuse(true);
            }

            container.start();

            // the application may itself run in a container (shared network) and needs the
            // network-internal address, while the test resource always runs on the host JVM
            // and needs the host-mapped one
            return new CamundaRunningDevService(FEATURE_NAME,
                    container.getContainerId(),
                    new CamundaContainerShutdownCloseable(container, FEATURE_NAME),
                    configMap(container.getGrpcApiAddress(), container.getRestApiAddress(),
                            container.getExternalGrpcApiAddress(), container.getExternalRestApiAddress(),
                            container.getMonitoringApiAddress(),
                            launchMode.isTest()));
        };

        return maybeContainerAddress
                .map(containerAddress -> {
                    URI url = URI.create(containerAddress.getUrl());
                    return new CamundaRunningDevService(FEATURE_NAME,
                            containerAddress.getId(),
                            null,
                            configMap(url, url, url, url, url, launchMode.isTest()));
                })
                .orElseGet(defaultCamundaBrokerSupplier);
    }

    private static Map<String, String> configMap(URI grpcApiUri, URI restApiUri,
            URI externalGrpcApiUri, URI externalRestApiUri, URI externalMonitoringApiUri,
            boolean test) {
        Map<String, String> config = new HashMap<>();
        config.put(PROP_CAMUNDA_GATEWAY_ADDRESS, grpcApiUri.toString());
        config.put(PROP_CAMUNDA_REST_ADDRESS, restApiUri.toString());

        if (test) {
            config.put("quarkiverse.camunda.devservices.test.gateway-address", externalGrpcApiUri.toString());
            config.put("quarkiverse.camunda.devservices.test.rest-address", externalRestApiUri.toString());
            config.put("quarkiverse.camunda.devservices.test.monitoring-address", externalMonitoringApiUri.toString());
        }
        return config;
    }

    private void shutdownCamunda() {
        if (devService != null) {
            try {
                devService.close();
            } catch (Throwable e) {
                log.error("Failed to stop Camunda", e);
            } finally {
                devService = null;
            }
        }
    }

    private CamundaDevServiceCfg getConfiguration(CamundaDevServiceBuildTimeConfig cfg) {
        CamundaDevServicesConfig camundaDevServicesConfig = cfg.devService();
        return new CamundaDevServiceCfg(camundaDevServicesConfig);
    }

    private static final class CamundaDevServiceCfg {
        private final boolean devServicesEnabled;
        private final String imageName;
        private final boolean shared;
        private final String serviceName;

        private final boolean reuse;

        public CamundaDevServiceCfg(CamundaDevServicesConfig config) {
            this.devServicesEnabled = config.enabled();
            this.imageName = config.imageName().orElse(null);
            this.shared = config.shared();
            this.serviceName = config.serviceName();
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
            CamundaDevServiceCfg that = (CamundaDevServiceCfg) o;
            return devServicesEnabled == that.devServicesEnabled && Objects.equals(imageName, that.imageName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(devServicesEnabled, imageName);
        }
    }

}

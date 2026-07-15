package io.quarkiverse.camunda.devservices;

import java.util.Optional;

import io.smallrye.config.WithDefault;
import io.smallrye.config.WithName;

public interface CamundaDevServicesConfig {

    /**
     * If DevServices has been explicitly enabled or disabled. DevServices is generally enabled
     * by default, unless there is an existing configuration present.
     * <p>
     * When DevServices is enabled Quarkus will attempt to automatically configure and start
     * a database when running in Dev or Test mode and when Docker is running.
     */
    @WithName("enabled")
    @WithDefault("true")
    boolean enabled();

    /**
     * Indicates if the Camunda server managed by Quarkus Dev Services is shared.
     * When shared, Quarkus looks for running containers using label-based service discovery.
     * If a matching container is found, it is used, and so a second one is not started.
     * Otherwise, Dev Services for Camunda starts a new container.
     * <p>
     * The discovery uses the {@code quarkus-dev-service-camunda} label.
     * The value is configured using the {@code service-name} property.
     * <p>
     * Container sharing is only used in dev mode.
     */
    @WithName("shared")
    @WithDefault("true")
    boolean shared();

    /**
     * The value of the {@code quarkus-dev-service-zeebe} label attached to the started container.
     * This property is used when {@code shared} is set to {@code true}.
     * In this case, before starting a container, Dev Services for Camunda looks for a container with the
     * {@code quarkus-dev-service-camunda} label
     * set to the configured value. If found, it will use this container instead of starting a new one. Otherwise, it
     * starts a new container with the {@code quarkus-dev-service-zeebe} label set to the specified value.
     * <p>
     * This property is used when you need multiple shared Zeebe servers.
     */
    @WithName("service-name")
    @WithDefault("camunda")
    String serviceName();

    /**
     * The container image name to use, for container based DevServices providers.
     */
    @WithName("image-name")
    Optional<String> imageName();

    /**
     * Helper to define the stop strategy for containers created by DevServices.
     * In particular, we don't want to actually stop the containers when they
     * have been flagged for reuse, and when the Test-containers configuration
     * has been explicitly set to allow container reuse.
     * To enable reuse, ass {@literal testcontainers.reuse.enable=true} in your
     * {@literal .testcontainers.properties} file, to be stored in your home.
     *
     * @see <a href="https://www.testcontainers.org/features/configuration/">Testcontainers Configuration</a>.
     */
    @WithName("reuse")
    @WithDefault("false")
    boolean reuse();

}

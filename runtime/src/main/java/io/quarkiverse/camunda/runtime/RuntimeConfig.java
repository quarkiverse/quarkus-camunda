package io.quarkiverse.camunda.runtime;

import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithName;

@ConfigRoot(phase = ConfigPhase.RUN_TIME)
@ConfigMapping(prefix = "quarkus.camunda")
public interface RuntimeConfig {

    /**
     * Camunda client broker configuration.
     */
    @WithName("client")
    ClientRuntimeConfig client();

    /**
     * Camunda client is active
     */
    @WithName("active")
    @WithDefault("true")
    boolean active();

}

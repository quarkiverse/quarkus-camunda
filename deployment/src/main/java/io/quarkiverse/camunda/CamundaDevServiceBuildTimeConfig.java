package io.quarkiverse.camunda;

import io.quarkiverse.camunda.devservices.CamundaDevServicesConfig;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithName;

@ConfigRoot(phase = ConfigPhase.BUILD_TIME)
@ConfigMapping(prefix = "quarkus.camunda")
public interface CamundaDevServiceBuildTimeConfig {

    /**
     * Default Dev services configuration.
     */
    @WithName("devservices")
    CamundaDevServicesConfig devService();

    /**
     * Dev mode configuration.
     */
    @WithName("dev-mode")
    CamundaDevMode devMode();

    interface CamundaDevMode {

        /**
         * Disable or enabled zeebe dashboard dev-ui.
         */
        @WithName("dev-ui.enabled")
        @WithDefault("true")
        boolean devUIEnabled();

        /**
         * Observe changes in the bpmn files.
         */
        @WithName("watch-bpmn-files")
        @WithDefault("true")
        boolean watchBpmnFiles();

        /**
         * Observe changes in the bpmn directory and subdirectories.
         */
        @WithName("watch-bpmn-dir")
        @WithDefault("true")
        boolean watchBpmnDir();

        /**
         * Observe changes in the job worker.
         */
        @WithName("watch-job-worker")
        @WithDefault("true")
        boolean watchJobWorker();
    }
}

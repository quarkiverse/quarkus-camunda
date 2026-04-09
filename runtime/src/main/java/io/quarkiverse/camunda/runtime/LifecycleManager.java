package io.quarkiverse.camunda.runtime;

import jakarta.enterprise.event.Observes;

import io.camunda.zeebe.client.ZeebeClient;
import io.quarkus.logging.Log;
import io.quarkus.runtime.Shutdown;
import io.quarkus.runtime.Startup;

public class LifecycleManager {

    // This method is used to eagerly create the camunda bean instance at RUNTIME_INIT execution time.
    void onStartup(@Observes Startup event, ZeebeClient client) {
        client.getConfiguration();
    }

    void onShutdown(@Observes Shutdown event, ZeebeClient client) {
        try {
            client.close();
        } catch (Exception e) {
            Log.error("The shutdown of the Zeebe client failed", e);
        }
    }
}

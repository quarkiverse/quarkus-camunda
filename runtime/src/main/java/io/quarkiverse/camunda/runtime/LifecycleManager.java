package io.quarkiverse.camunda.runtime;

import jakarta.enterprise.event.Observes;

import io.camunda.client.CamundaClient;
import io.quarkus.logging.Log;
import io.quarkus.runtime.Shutdown;
import io.quarkus.runtime.Startup;

public class LifecycleManager {

    // This method is used to eagerly create the camunda bean instance at RUNTIME_INIT execution time.
    void onStartup(@Observes Startup event, CamundaClient client) {
        client.getConfiguration();
    }

    void onShutdown(@Observes Shutdown event, CamundaClient client) {
        try {
            client.close();
        } catch (Exception e) {
            Log.error("The shutdown of the Zeebe client failed", e);
        }
    }
}

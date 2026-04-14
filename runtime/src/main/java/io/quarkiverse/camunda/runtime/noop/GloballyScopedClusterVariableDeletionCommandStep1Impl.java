package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.GloballyScopedClusterVariableDeletionCommandStep1;
import io.camunda.client.api.response.DeleteClusterVariableResponse;

public class GloballyScopedClusterVariableDeletionCommandStep1Impl extends AbstractStep<DeleteClusterVariableResponse>
        implements GloballyScopedClusterVariableDeletionCommandStep1 {

    @Override
    public GloballyScopedClusterVariableDeletionCommandStep1 delete(String name) {
        return this;
    }
}

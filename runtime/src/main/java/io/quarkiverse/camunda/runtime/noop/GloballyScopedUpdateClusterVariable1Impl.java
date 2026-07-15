package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.GloballyScopedClusterVariableUpdateCommandStep1;
import io.camunda.client.api.response.UpdateClusterVariableResponse;

public class GloballyScopedUpdateClusterVariable1Impl extends AbstractStep<UpdateClusterVariableResponse>
        implements GloballyScopedClusterVariableUpdateCommandStep1 {

    @Override
    public GloballyScopedClusterVariableUpdateCommandStep1 update(String name, Object value) {
        return this;
    }
}

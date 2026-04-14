package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.GloballyScopedClusterVariableCreationCommandStep1;
import io.camunda.client.api.response.CreateClusterVariableResponse;

public class GloballyScopedCreateClusterVariable1Impl extends AbstractStep<CreateClusterVariableResponse>
        implements GloballyScopedClusterVariableCreationCommandStep1 {

    @Override
    public GloballyScopedClusterVariableCreationCommandStep1 create(String name, Object value) {
        return this;
    }
}

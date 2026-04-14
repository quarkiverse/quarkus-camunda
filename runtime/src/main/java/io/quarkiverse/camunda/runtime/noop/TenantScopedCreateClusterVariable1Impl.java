package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.TenantScopedClusterVariableCreationCommandStep1;
import io.camunda.client.api.response.CreateClusterVariableResponse;

public class TenantScopedCreateClusterVariable1Impl extends AbstractStep<CreateClusterVariableResponse>
        implements TenantScopedClusterVariableCreationCommandStep1 {

    @Override
    public TenantScopedClusterVariableCreationCommandStep1 create(String name, Object value) {
        return this;
    }
}

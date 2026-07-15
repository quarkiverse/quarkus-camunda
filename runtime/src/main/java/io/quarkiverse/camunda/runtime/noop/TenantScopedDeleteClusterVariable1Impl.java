package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.TenantScopedClusterVariableDeletionCommandStep1;
import io.camunda.client.api.response.DeleteClusterVariableResponse;

public class TenantScopedDeleteClusterVariable1Impl extends AbstractStep<DeleteClusterVariableResponse>
        implements TenantScopedClusterVariableDeletionCommandStep1 {

    @Override
    public TenantScopedClusterVariableDeletionCommandStep1 delete(String name) {
        return this;
    }
}

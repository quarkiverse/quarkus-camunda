package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.DeleteTenantCommandStep1;
import io.camunda.client.api.response.DeleteTenantResponse;

public class DeleteTenantCommand1Impl extends AbstractStep<DeleteTenantResponse> implements DeleteTenantCommandStep1 {

    @Override
    public DeleteTenantCommandStep1 tenantId(String tenantId) {
        return this;
    }
}

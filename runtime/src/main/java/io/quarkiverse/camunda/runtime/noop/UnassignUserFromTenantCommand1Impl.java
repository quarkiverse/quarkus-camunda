package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.UnassignUserFromTenantCommandStep1;
import io.camunda.client.api.response.UnassignUserFromTenantResponse;

public class UnassignUserFromTenantCommand1Impl extends AbstractStep<UnassignUserFromTenantResponse>
        implements UnassignUserFromTenantCommandStep1, UnassignUserFromTenantCommandStep1.UnassignUserFromTenantCommandStep2,
        UnassignUserFromTenantCommandStep1.UnassignUserFromTenantCommandStep3 {

    @Override
    public UnassignUserFromTenantCommandStep2 username(String username) {
        return this;
    }

    @Override
    public UnassignUserFromTenantCommandStep3 tenantId(String tenantId) {
        return this;
    }
}

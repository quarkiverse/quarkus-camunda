package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.UnassignRoleFromTenantCommandStep1;
import io.camunda.client.api.response.UnassignRoleFromTenantResponse;

public class UnassignRoleFromTenantCommand1Impl extends AbstractStep<UnassignRoleFromTenantResponse>
        implements UnassignRoleFromTenantCommandStep1, UnassignRoleFromTenantCommandStep1.UnassignRoleFromTenantCommandStep2,
        UnassignRoleFromTenantCommandStep1.UnassignRoleFromTenantCommandStep3 {

    @Override
    public UnassignRoleFromTenantCommandStep2 roleId(String roleId) {
        return this;
    }

    @Override
    public UnassignRoleFromTenantCommandStep3 tenantId(String tenantId) {
        return this;
    }
}

package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.AssignRoleToTenantCommandStep1;
import io.camunda.client.api.response.AssignRoleToTenantResponse;

public class AssignRoleToTenantCommand1Impl extends AbstractStep<AssignRoleToTenantResponse>
        implements AssignRoleToTenantCommandStep1, AssignRoleToTenantCommandStep1.AssignRoleToTenantCommandStep2,
        AssignRoleToTenantCommandStep1.AssignRoleToTenantCommandStep3 {

    @Override
    public AssignRoleToTenantCommandStep2 roleId(String roleId) {
        return this;
    }

    @Override
    public AssignRoleToTenantCommandStep3 tenantId(String tenantId) {
        return this;
    }
}

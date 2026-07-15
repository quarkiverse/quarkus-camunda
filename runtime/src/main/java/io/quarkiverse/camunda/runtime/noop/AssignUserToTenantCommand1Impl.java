package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.AssignUserToTenantCommandStep1;
import io.camunda.client.api.response.AssignUserToTenantResponse;

public class AssignUserToTenantCommand1Impl extends AbstractStep<AssignUserToTenantResponse>
        implements AssignUserToTenantCommandStep1, AssignUserToTenantCommandStep1.AssignUserToTenantCommandStep2,
        AssignUserToTenantCommandStep1.AssignUserToTenantCommandStep3 {

    @Override
    public AssignUserToTenantCommandStep2 username(String username) {
        return this;
    }

    @Override
    public AssignUserToTenantCommandStep3 tenantId(String tenantId) {
        return this;
    }
}

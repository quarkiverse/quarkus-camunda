package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.AssignGroupToTenantCommandStep1;
import io.camunda.client.api.response.AssignGroupToTenantResponse;

public class AssignGroupToTenantCommand1Impl extends AbstractStep<AssignGroupToTenantResponse>
        implements AssignGroupToTenantCommandStep1, AssignGroupToTenantCommandStep1.AssignGroupToTenantCommandStep2,
        AssignGroupToTenantCommandStep1.AssignGroupToTenantCommandStep3 {

    @Override
    public AssignGroupToTenantCommandStep2 groupId(String groupId) {
        return this;
    }

    @Override
    public AssignGroupToTenantCommandStep3 tenantId(String tenantId) {
        return this;
    }
}

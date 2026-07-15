package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.UnassignGroupFromTenantCommandStep1;
import io.camunda.client.api.response.UnassignGroupFromTenantResponse;

public class UnassignGroupFromTenantCommand1Impl extends AbstractStep<UnassignGroupFromTenantResponse>
        implements UnassignGroupFromTenantCommandStep1, UnassignGroupFromTenantCommandStep1.UnassignGroupFromTenantCommandStep2,
        UnassignGroupFromTenantCommandStep1.UnassignGroupFromTenantCommandStep3 {

    @Override
    public UnassignGroupFromTenantCommandStep2 groupId(String groupId) {
        return this;
    }

    @Override
    public UnassignGroupFromTenantCommandStep3 tenantId(String tenantId) {
        return this;
    }
}

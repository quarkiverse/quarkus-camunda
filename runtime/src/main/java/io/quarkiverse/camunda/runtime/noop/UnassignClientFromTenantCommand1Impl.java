package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.UnassignClientFromTenantCommandStep1;
import io.camunda.client.api.response.UnassignClientFromTenantResponse;

public class UnassignClientFromTenantCommand1Impl extends AbstractStep<UnassignClientFromTenantResponse>
        implements UnassignClientFromTenantCommandStep1,
        UnassignClientFromTenantCommandStep1.UnassignClientFromTenantCommandStep2,
        UnassignClientFromTenantCommandStep1.UnassignClientFromTenantCommandStep3 {

    @Override
    public UnassignClientFromTenantCommandStep2 clientId(String clientId) {
        return this;
    }

    @Override
    public UnassignClientFromTenantCommandStep3 tenantId(String tenantId) {
        return this;
    }
}

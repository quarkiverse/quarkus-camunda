package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.AssignClientToTenantCommandStep1;
import io.camunda.client.api.response.AssignClientToTenantResponse;

public class AssignClientToTenantCommandStep1Impl extends AbstractStep<AssignClientToTenantResponse>
        implements AssignClientToTenantCommandStep1, AssignClientToTenantCommandStep1.AssignClientToTenantCommandStep2,
        AssignClientToTenantCommandStep1.AssignClientToTenantCommandStep3 {

    @Override
    public AssignClientToTenantCommandStep2 clientId(String clientId) {
        return this;
    }

    @Override
    public AssignClientToTenantCommandStep3 tenantId(String tenantId) {
        return this;
    }
}

package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.AssignMappingRuleToTenantCommandStep1;
import io.camunda.client.api.response.AssignMappingRuleToTenantResponse;

public class AssignMappingRuleToTenantCommand1Impl extends AbstractStep<AssignMappingRuleToTenantResponse> implements
        AssignMappingRuleToTenantCommandStep1, AssignMappingRuleToTenantCommandStep1.AssignMappingRuleToTenantCommandStep2,
        AssignMappingRuleToTenantCommandStep1.AssignMappingRuleToTenantCommandStep3 {

    @Override
    public AssignMappingRuleToTenantCommandStep3 tenantId(String tenantId) {
        return this;
    }

    @Override
    public AssignMappingRuleToTenantCommandStep2 mappingRuleId(String mappingRuleId) {
        return this;
    }
}

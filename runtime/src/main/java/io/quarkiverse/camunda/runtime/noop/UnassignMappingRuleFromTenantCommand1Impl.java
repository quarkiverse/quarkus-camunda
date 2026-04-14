package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.UnassignMappingRuleFromTenantCommandStep1;
import io.camunda.client.api.response.UnassignMappingRuleFromTenantResponse;

public class UnassignMappingRuleFromTenantCommand1Impl extends AbstractStep<UnassignMappingRuleFromTenantResponse>
        implements UnassignMappingRuleFromTenantCommandStep1,
        UnassignMappingRuleFromTenantCommandStep1.UnassignMappingRuleFromTenantCommandStep2,
        UnassignMappingRuleFromTenantCommandStep1.UnassignMappingRuleFromTenantCommandStep3 {

    @Override
    public UnassignMappingRuleFromTenantCommandStep2 mappingRuleId(String mappingRuleId) {
        return this;
    }

    @Override
    public UnassignMappingRuleFromTenantCommandStep3 tenantId(String tenantId) {
        return this;
    }
}

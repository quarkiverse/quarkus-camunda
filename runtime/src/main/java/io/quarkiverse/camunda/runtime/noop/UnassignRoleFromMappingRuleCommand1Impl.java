package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.UnassignRoleFromMappingRuleCommandStep1;
import io.camunda.client.api.response.UnassignRoleFromMappingRuleResponse;

public class UnassignRoleFromMappingRuleCommand1Impl extends AbstractStep<UnassignRoleFromMappingRuleResponse>
        implements UnassignRoleFromMappingRuleCommandStep1,
        UnassignRoleFromMappingRuleCommandStep1.UnassignRoleFromMappingRuleCommandStep2,
        UnassignRoleFromMappingRuleCommandStep1.UnassignRoleFromMappingRuleCommandStep3 {

    @Override
    public UnassignRoleFromMappingRuleCommandStep2 roleId(String roleId) {
        return this;
    }

    @Override
    public UnassignRoleFromMappingRuleCommandStep3 mappingRuleId(String mappingRuleId) {
        return this;
    }
}

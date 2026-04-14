package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.AssignRoleToMappingRuleCommandStep1;
import io.camunda.client.api.response.AssignRoleToMappingRuleResponse;

public class AssignRoleToMappingRuleCommand1Impl extends AbstractStep<AssignRoleToMappingRuleResponse>
        implements AssignRoleToMappingRuleCommandStep1, AssignRoleToMappingRuleCommandStep1.AssignRoleToMappingRuleCommandStep2,
        AssignRoleToMappingRuleCommandStep1.AssignRoleToMappingRuleCommandStep3 {

    @Override
    public AssignRoleToMappingRuleCommandStep3 mappingRuleId(String mappingRuleId) {
        return this;
    }

    @Override
    public AssignRoleToMappingRuleCommandStep2 roleId(String roleId) {
        return this;
    }
}

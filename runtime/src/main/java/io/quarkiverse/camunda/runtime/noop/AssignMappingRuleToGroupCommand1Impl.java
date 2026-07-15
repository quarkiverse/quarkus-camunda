package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.AssignMappingRuleToGroupStep1;
import io.camunda.client.api.response.AssignMappingRuleToGroupResponse;

public class AssignMappingRuleToGroupCommand1Impl extends AbstractStep<AssignMappingRuleToGroupResponse>
        implements AssignMappingRuleToGroupStep1, AssignMappingRuleToGroupStep1.AssignMappingRuleToGroupStep2,
        AssignMappingRuleToGroupStep1.AssignMappingRuleToGroupStep3 {
    @Override
    public AssignMappingRuleToGroupStep2 mappingRuleId(String mappingRuleId) {
        return this;
    }

    @Override
    public AssignMappingRuleToGroupStep3 groupId(String groupId) {
        return this;
    }
}

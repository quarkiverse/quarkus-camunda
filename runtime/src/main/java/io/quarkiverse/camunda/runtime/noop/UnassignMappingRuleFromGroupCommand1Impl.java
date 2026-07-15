package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.UnassignMappingRuleFromGroupStep1;
import io.camunda.client.api.response.UnassignMappingRuleFromGroupResponse;

public class UnassignMappingRuleFromGroupCommand1Impl extends AbstractStep<UnassignMappingRuleFromGroupResponse>
        implements UnassignMappingRuleFromGroupStep1, UnassignMappingRuleFromGroupStep1.UnassignMappingRuleFromGroupStep2,
        UnassignMappingRuleFromGroupStep1.UnassignMappingRuleFromGroupStep3 {

    @Override
    public UnassignMappingRuleFromGroupStep2 mappingRuleId(String mappingRuleId) {
        return this;
    }

    @Override
    public UnassignMappingRuleFromGroupStep3 groupId(String groupId) {
        return this;
    }
}

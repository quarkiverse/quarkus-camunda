package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.UpdateMappingRuleCommandStep1;
import io.camunda.client.api.response.UpdateMappingRuleResponse;

public class UpdateMappingRuleCommand1Impl extends AbstractStep<UpdateMappingRuleResponse>
        implements UpdateMappingRuleCommandStep1, UpdateMappingRuleCommandStep1.UpdateMappingRuleCommandStep2,
        UpdateMappingRuleCommandStep1.UpdateMappingRuleCommandStep3,
        UpdateMappingRuleCommandStep1.UpdateMappingRuleCommandStep4 {

    @Override
    public UpdateMappingRuleCommandStep2 name(String name) {
        return this;
    }

    @Override
    public UpdateMappingRuleCommandStep3 claimName(String claimName) {
        return this;
    }

    @Override
    public UpdateMappingRuleCommandStep4 claimValue(String claimValue) {
        return this;
    }
}

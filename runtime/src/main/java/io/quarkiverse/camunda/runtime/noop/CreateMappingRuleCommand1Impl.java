package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.CreateMappingRuleCommandStep1;
import io.camunda.client.api.response.CreateMappingRuleResponse;

public class CreateMappingRuleCommand1Impl extends AbstractStep<CreateMappingRuleResponse>
        implements CreateMappingRuleCommandStep1 {

    @Override
    public CreateMappingRuleCommandStep1 claimName(String claimName) {
        return this;
    }

    @Override
    public CreateMappingRuleCommandStep1 claimValue(String claimValue) {
        return this;
    }

    @Override
    public CreateMappingRuleCommandStep1 name(String name) {
        return this;
    }

    @Override
    public CreateMappingRuleCommandStep1 mappingRuleId(String mappingRuleId) {
        return this;
    }
}

package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.MappingRuleFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.MappingRulesByRoleSearchRequest;
import io.camunda.client.api.search.response.MappingRule;
import io.camunda.client.api.search.sort.MappingRuleSort;

public class MappingRulesByRoleSearchRequest1Impl extends AbstractFinalSearchRequestStep<MappingRule>
        implements MappingRulesByRoleSearchRequest {

    @Override
    public MappingRulesByRoleSearchRequest filter(MappingRuleFilter value) {
        return this;
    }

    @Override
    public MappingRulesByRoleSearchRequest filter(Consumer<MappingRuleFilter> fn) {
        return this;
    }

    @Override
    public MappingRulesByRoleSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public MappingRulesByRoleSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public MappingRulesByRoleSearchRequest sort(MappingRuleSort value) {
        return this;
    }

    @Override
    public MappingRulesByRoleSearchRequest sort(Consumer<MappingRuleSort> fn) {
        return this;
    }
}

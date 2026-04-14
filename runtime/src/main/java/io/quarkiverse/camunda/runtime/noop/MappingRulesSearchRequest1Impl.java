package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.MappingRuleFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.MappingRulesSearchRequest;
import io.camunda.client.api.search.response.MappingRule;
import io.camunda.client.api.search.sort.MappingRuleSort;

public class MappingRulesSearchRequest1Impl extends AbstractFinalSearchRequestStep<MappingRule>
        implements MappingRulesSearchRequest {

    @Override
    public MappingRulesSearchRequest filter(MappingRuleFilter value) {
        return this;
    }

    @Override
    public MappingRulesSearchRequest filter(Consumer<MappingRuleFilter> fn) {
        return this;
    }

    @Override
    public MappingRulesSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public MappingRulesSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public MappingRulesSearchRequest sort(MappingRuleSort value) {
        return this;
    }

    @Override
    public MappingRulesSearchRequest sort(Consumer<MappingRuleSort> fn) {
        return this;
    }
}

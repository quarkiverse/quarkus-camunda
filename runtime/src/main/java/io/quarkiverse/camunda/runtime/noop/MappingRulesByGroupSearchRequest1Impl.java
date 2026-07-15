package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.MappingRuleFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.MappingRulesByGroupSearchRequest;
import io.camunda.client.api.search.response.MappingRule;
import io.camunda.client.api.search.sort.MappingRuleSort;

public class MappingRulesByGroupSearchRequest1Impl extends AbstractFinalSearchRequestStep<MappingRule>
        implements MappingRulesByGroupSearchRequest {

    @Override
    public MappingRulesByGroupSearchRequest filter(MappingRuleFilter value) {
        return this;
    }

    @Override
    public MappingRulesByGroupSearchRequest filter(Consumer<MappingRuleFilter> fn) {
        return this;
    }

    @Override
    public MappingRulesByGroupSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public MappingRulesByGroupSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public MappingRulesByGroupSearchRequest sort(MappingRuleSort value) {
        return this;
    }

    @Override
    public MappingRulesByGroupSearchRequest sort(Consumer<MappingRuleSort> fn) {
        return this;
    }
}

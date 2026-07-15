package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.MappingRuleFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.MappingRulesByTenantSearchRequest;
import io.camunda.client.api.search.response.MappingRule;
import io.camunda.client.api.search.sort.MappingRuleSort;

public class MappingRulesByTenantSearchRequest1Impl extends AbstractFinalSearchRequestStep<MappingRule>
        implements MappingRulesByTenantSearchRequest {

    @Override
    public MappingRulesByTenantSearchRequest filter(MappingRuleFilter value) {
        return this;
    }

    @Override
    public MappingRulesByTenantSearchRequest filter(Consumer<MappingRuleFilter> fn) {
        return this;
    }

    @Override
    public MappingRulesByTenantSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public MappingRulesByTenantSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public MappingRulesByTenantSearchRequest sort(MappingRuleSort value) {
        return this;
    }

    @Override
    public MappingRulesByTenantSearchRequest sort(Consumer<MappingRuleSort> fn) {
        return this;
    }
}

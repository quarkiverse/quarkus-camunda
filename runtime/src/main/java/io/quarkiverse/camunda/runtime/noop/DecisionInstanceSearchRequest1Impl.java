package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.DecisionInstanceFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.DecisionInstanceSearchRequest;
import io.camunda.client.api.search.response.DecisionInstance;
import io.camunda.client.api.search.sort.DecisionInstanceSort;

public class DecisionInstanceSearchRequest1Impl extends AbstractFinalSearchRequestStep<DecisionInstance>
        implements DecisionInstanceSearchRequest {

    @Override
    public DecisionInstanceSearchRequest filter(DecisionInstanceFilter value) {
        return this;
    }

    @Override
    public DecisionInstanceSearchRequest filter(Consumer<DecisionInstanceFilter> fn) {
        return this;
    }

    @Override
    public DecisionInstanceSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public DecisionInstanceSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public DecisionInstanceSearchRequest sort(DecisionInstanceSort value) {
        return this;
    }

    @Override
    public DecisionInstanceSearchRequest sort(Consumer<DecisionInstanceSort> fn) {
        return this;
    }
}

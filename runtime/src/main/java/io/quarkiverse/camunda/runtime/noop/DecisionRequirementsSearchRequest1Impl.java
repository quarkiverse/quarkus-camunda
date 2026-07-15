package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.DecisionRequirementsFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.DecisionRequirementsSearchRequest;
import io.camunda.client.api.search.response.DecisionRequirements;
import io.camunda.client.api.search.sort.DecisionRequirementsSort;

public class DecisionRequirementsSearchRequest1Impl extends AbstractFinalSearchRequestStep<DecisionRequirements>
        implements DecisionRequirementsSearchRequest {

    @Override
    public DecisionRequirementsSearchRequest filter(DecisionRequirementsFilter value) {
        return this;
    }

    @Override
    public DecisionRequirementsSearchRequest filter(Consumer<DecisionRequirementsFilter> fn) {
        return this;
    }

    @Override
    public DecisionRequirementsSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public DecisionRequirementsSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public DecisionRequirementsSearchRequest sort(DecisionRequirementsSort value) {
        return this;
    }

    @Override
    public DecisionRequirementsSearchRequest sort(Consumer<DecisionRequirementsSort> fn) {
        return this;
    }
}

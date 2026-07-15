package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.DecisionDefinitionFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.DecisionDefinitionSearchRequest;
import io.camunda.client.api.search.response.DecisionDefinition;
import io.camunda.client.api.search.sort.DecisionDefinitionSort;

public class DecisionDefinitionSearchRequest1Impl extends AbstractFinalSearchRequestStep<DecisionDefinition>
        implements DecisionDefinitionSearchRequest {

    @Override
    public DecisionDefinitionSearchRequest filter(DecisionDefinitionFilter value) {
        return this;
    }

    @Override
    public DecisionDefinitionSearchRequest filter(Consumer<DecisionDefinitionFilter> fn) {
        return this;
    }

    @Override
    public DecisionDefinitionSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public DecisionDefinitionSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public DecisionDefinitionSearchRequest sort(DecisionDefinitionSort value) {
        return this;
    }

    @Override
    public DecisionDefinitionSearchRequest sort(Consumer<DecisionDefinitionSort> fn) {
        return this;
    }
}

package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.VariableFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.VariableSearchRequest;
import io.camunda.client.api.search.response.Variable;
import io.camunda.client.api.search.sort.VariableSort;

public class VariableSearchRequest1Impl extends AbstractFinalSearchRequestStep<Variable> implements VariableSearchRequest {

    @Override
    public VariableSearchRequest withFullValues() {
        return this;
    }

    @Override
    public VariableSearchRequest filter(VariableFilter value) {
        return this;
    }

    @Override
    public VariableSearchRequest filter(Consumer<VariableFilter> fn) {
        return this;
    }

    @Override
    public VariableSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public VariableSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public VariableSearchRequest sort(VariableSort value) {
        return this;
    }

    @Override
    public VariableSearchRequest sort(Consumer<VariableSort> fn) {
        return this;
    }
}

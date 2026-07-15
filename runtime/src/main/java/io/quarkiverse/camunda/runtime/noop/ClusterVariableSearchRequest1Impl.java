package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.ClusterVariableFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.ClusterVariableSearchRequest;
import io.camunda.client.api.search.response.ClusterVariable;
import io.camunda.client.api.search.sort.ClusterVariableSort;

public class ClusterVariableSearchRequest1Impl extends AbstractFinalSearchRequestStep<ClusterVariable>
        implements ClusterVariableSearchRequest {

    @Override
    public ClusterVariableSearchRequest withFullValues() {
        return this;
    }

    @Override
    public ClusterVariableSearchRequest filter(ClusterVariableFilter value) {
        return this;
    }

    @Override
    public ClusterVariableSearchRequest filter(Consumer<ClusterVariableFilter> fn) {
        return this;
    }

    @Override
    public ClusterVariableSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public ClusterVariableSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public ClusterVariableSearchRequest sort(ClusterVariableSort value) {
        return this;
    }

    @Override
    public ClusterVariableSearchRequest sort(Consumer<ClusterVariableSort> fn) {
        return this;
    }
}

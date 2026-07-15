package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.UserTaskVariableFilter;
import io.camunda.client.api.search.page.OffsetPage;
import io.camunda.client.api.search.request.UserTaskEffectiveVariableSearchRequest;
import io.camunda.client.api.search.response.Variable;
import io.camunda.client.api.search.sort.VariableSort;

public class UserTaskEffectiveVariableSearchRequest1Impl extends AbstractFinalSearchRequestStep<Variable>
        implements UserTaskEffectiveVariableSearchRequest {

    @Override
    public UserTaskEffectiveVariableSearchRequest withFullValues() {
        return this;
    }

    @Override
    public UserTaskEffectiveVariableSearchRequest filter(UserTaskVariableFilter value) {
        return this;
    }

    @Override
    public UserTaskEffectiveVariableSearchRequest filter(Consumer<UserTaskVariableFilter> fn) {
        return this;
    }

    @Override
    public UserTaskEffectiveVariableSearchRequest page(OffsetPage value) {
        return this;
    }

    @Override
    public UserTaskEffectiveVariableSearchRequest page(Consumer<OffsetPage> fn) {
        return this;
    }

    @Override
    public UserTaskEffectiveVariableSearchRequest sort(VariableSort value) {
        return this;
    }

    @Override
    public UserTaskEffectiveVariableSearchRequest sort(Consumer<VariableSort> fn) {
        return this;
    }
}

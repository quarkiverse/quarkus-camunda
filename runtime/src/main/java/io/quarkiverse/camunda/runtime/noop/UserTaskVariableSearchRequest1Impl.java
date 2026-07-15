package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.UserTaskVariableFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.UserTaskVariableSearchRequest;
import io.camunda.client.api.search.response.Variable;
import io.camunda.client.api.search.sort.VariableSort;

public class UserTaskVariableSearchRequest1Impl extends AbstractFinalSearchRequestStep<Variable>
        implements UserTaskVariableSearchRequest {

    @Override
    public UserTaskVariableSearchRequest withFullValues() {
        return this;
    }

    @Override
    public UserTaskVariableSearchRequest filter(UserTaskVariableFilter value) {
        return this;
    }

    @Override
    public UserTaskVariableSearchRequest filter(Consumer<UserTaskVariableFilter> fn) {
        return this;
    }

    @Override
    public UserTaskVariableSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public UserTaskVariableSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public UserTaskVariableSearchRequest sort(VariableSort value) {
        return this;
    }

    @Override
    public UserTaskVariableSearchRequest sort(Consumer<VariableSort> fn) {
        return this;
    }
}

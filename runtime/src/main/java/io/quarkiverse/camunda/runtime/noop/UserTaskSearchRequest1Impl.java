package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.UserTaskFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.UserTaskSearchRequest;
import io.camunda.client.api.search.response.UserTask;
import io.camunda.client.api.search.sort.UserTaskSort;

public class UserTaskSearchRequest1Impl extends AbstractFinalSearchRequestStep<UserTask> implements UserTaskSearchRequest {
    @Override
    public UserTaskSearchRequest filter(UserTaskFilter value) {
        return this;
    }

    @Override
    public UserTaskSearchRequest filter(Consumer<UserTaskFilter> fn) {
        return this;
    }

    @Override
    public UserTaskSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public UserTaskSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public UserTaskSearchRequest sort(UserTaskSort value) {
        return this;
    }

    @Override
    public UserTaskSearchRequest sort(Consumer<UserTaskSort> fn) {
        return this;
    }
}

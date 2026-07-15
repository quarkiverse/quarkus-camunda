package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.UserFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.UsersSearchRequest;
import io.camunda.client.api.search.response.User;
import io.camunda.client.api.search.sort.UserSort;

public class UsersSearchRequest1Impl extends AbstractFinalSearchRequestStep<User> implements UsersSearchRequest {

    @Override
    public UsersSearchRequest filter(UserFilter value) {
        return this;
    }

    @Override
    public UsersSearchRequest filter(Consumer<UserFilter> fn) {
        return this;
    }

    @Override
    public UsersSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public UsersSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public UsersSearchRequest sort(UserSort value) {
        return this;
    }

    @Override
    public UsersSearchRequest sort(Consumer<UserSort> fn) {
        return this;
    }
}

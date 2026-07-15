package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.fetch.AuthorizationsSearchRequest;
import io.camunda.client.api.search.filter.AuthorizationFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.response.Authorization;
import io.camunda.client.api.search.sort.AuthorizationSort;

public class AuthorizationsSearchRequest1Impl extends AbstractFinalSearchRequestStep<Authorization>
        implements AuthorizationsSearchRequest {

    @Override
    public AuthorizationsSearchRequest filter(AuthorizationFilter value) {
        return this;
    }

    @Override
    public AuthorizationsSearchRequest filter(Consumer<AuthorizationFilter> fn) {
        return this;
    }

    @Override
    public AuthorizationsSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public AuthorizationsSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public AuthorizationsSearchRequest sort(AuthorizationSort value) {
        return this;
    }

    @Override
    public AuthorizationsSearchRequest sort(Consumer<AuthorizationSort> fn) {
        return this;
    }
}

package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.fetch.RolesSearchRequest;
import io.camunda.client.api.search.filter.RoleFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.response.Role;
import io.camunda.client.api.search.sort.RoleSort;

public class RolesSearchRequest1Impl extends AbstractFinalSearchRequestStep<Role> implements RolesSearchRequest {

    @Override
    public RolesSearchRequest filter(RoleFilter value) {
        return this;
    }

    @Override
    public RolesSearchRequest filter(Consumer<RoleFilter> fn) {
        return this;
    }

    @Override
    public RolesSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public RolesSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public RolesSearchRequest sort(RoleSort value) {
        return this;
    }

    @Override
    public RolesSearchRequest sort(Consumer<RoleSort> fn) {
        return this;
    }
}

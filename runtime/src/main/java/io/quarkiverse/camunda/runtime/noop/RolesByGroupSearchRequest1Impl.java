package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.RoleFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.RolesByGroupSearchRequest;
import io.camunda.client.api.search.response.Role;
import io.camunda.client.api.search.sort.RoleSort;

public class RolesByGroupSearchRequest1Impl extends AbstractFinalSearchRequestStep<Role> implements RolesByGroupSearchRequest {

    @Override
    public RolesByGroupSearchRequest filter(RoleFilter value) {
        return this;
    }

    @Override
    public RolesByGroupSearchRequest filter(Consumer<RoleFilter> fn) {
        return this;
    }

    @Override
    public RolesByGroupSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public RolesByGroupSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public RolesByGroupSearchRequest sort(RoleSort value) {
        return this;
    }

    @Override
    public RolesByGroupSearchRequest sort(Consumer<RoleSort> fn) {
        return this;
    }
}

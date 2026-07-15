package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.RoleFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.RolesByTenantSearchRequest;
import io.camunda.client.api.search.response.Role;
import io.camunda.client.api.search.sort.RoleSort;

public class RolesByTenantSearchRequest1Impl extends AbstractFinalSearchRequestStep<Role>
        implements RolesByTenantSearchRequest {

    @Override
    public RolesByTenantSearchRequest filter(RoleFilter value) {
        return this;
    }

    @Override
    public RolesByTenantSearchRequest filter(Consumer<RoleFilter> fn) {
        return this;
    }

    @Override
    public RolesByTenantSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public RolesByTenantSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public RolesByTenantSearchRequest sort(RoleSort value) {
        return this;
    }

    @Override
    public RolesByTenantSearchRequest sort(Consumer<RoleSort> fn) {
        return this;
    }
}

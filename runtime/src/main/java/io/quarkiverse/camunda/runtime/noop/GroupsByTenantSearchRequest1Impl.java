package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.TenantGroupFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.GroupsByTenantSearchRequest;
import io.camunda.client.api.search.response.TenantGroup;
import io.camunda.client.api.search.sort.TenantGroupSort;

public class GroupsByTenantSearchRequest1Impl extends AbstractFinalSearchRequestStep<TenantGroup>
        implements GroupsByTenantSearchRequest {

    @Override
    public GroupsByTenantSearchRequest filter(TenantGroupFilter value) {
        return this;
    }

    @Override
    public GroupsByTenantSearchRequest filter(Consumer<TenantGroupFilter> fn) {
        return this;
    }

    @Override
    public GroupsByTenantSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public GroupsByTenantSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public GroupsByTenantSearchRequest sort(TenantGroupSort value) {
        return this;
    }

    @Override
    public GroupsByTenantSearchRequest sort(Consumer<TenantGroupSort> fn) {
        return this;
    }
}

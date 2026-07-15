package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.TenantFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.TenantsSearchRequest;
import io.camunda.client.api.search.response.Tenant;
import io.camunda.client.api.search.sort.TenantSort;

public class TenantsSearchRequest1Impl extends AbstractFinalSearchRequestStep<Tenant> implements TenantsSearchRequest {

    @Override
    public TenantsSearchRequest filter(TenantFilter value) {
        return this;
    }

    @Override
    public TenantsSearchRequest filter(Consumer<TenantFilter> fn) {
        return this;
    }

    @Override
    public TenantsSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public TenantsSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public TenantsSearchRequest sort(TenantSort value) {
        return this;
    }

    @Override
    public TenantsSearchRequest sort(Consumer<TenantSort> fn) {
        return this;
    }
}

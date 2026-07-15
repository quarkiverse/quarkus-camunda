package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.TenantUserFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.UsersByTenantSearchRequest;
import io.camunda.client.api.search.response.TenantUser;
import io.camunda.client.api.search.sort.TenantUserSort;

public class UsersByTenantSearchRequest1Impl extends AbstractFinalSearchRequestStep<TenantUser>
        implements UsersByTenantSearchRequest {

    @Override
    public UsersByTenantSearchRequest filter(TenantUserFilter value) {
        return this;
    }

    @Override
    public UsersByTenantSearchRequest filter(Consumer<TenantUserFilter> fn) {
        return this;
    }

    @Override
    public UsersByTenantSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public UsersByTenantSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public UsersByTenantSearchRequest sort(TenantUserSort value) {
        return this;
    }

    @Override
    public UsersByTenantSearchRequest sort(Consumer<TenantUserSort> fn) {
        return this;
    }
}

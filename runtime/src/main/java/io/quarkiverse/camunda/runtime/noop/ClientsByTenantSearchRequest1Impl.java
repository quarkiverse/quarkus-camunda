package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.ClientFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.ClientsByTenantSearchRequest;
import io.camunda.client.api.search.response.Client;
import io.camunda.client.api.search.sort.ClientSort;

public class ClientsByTenantSearchRequest1Impl extends AbstractFinalSearchRequestStep<Client>
        implements ClientsByTenantSearchRequest {

    @Override
    public ClientsByTenantSearchRequest filter(ClientFilter value) {
        return this;
    }

    @Override
    public ClientsByTenantSearchRequest filter(Consumer<ClientFilter> fn) {
        return this;
    }

    @Override
    public ClientsByTenantSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public ClientsByTenantSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public ClientsByTenantSearchRequest sort(ClientSort value) {
        return this;
    }

    @Override
    public ClientsByTenantSearchRequest sort(Consumer<ClientSort> fn) {
        return this;
    }
}

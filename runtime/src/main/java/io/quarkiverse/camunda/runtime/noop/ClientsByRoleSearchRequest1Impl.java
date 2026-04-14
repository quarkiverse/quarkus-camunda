package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.ClientFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.ClientsByRoleSearchRequest;
import io.camunda.client.api.search.response.Client;
import io.camunda.client.api.search.sort.ClientSort;

public class ClientsByRoleSearchRequest1Impl extends AbstractFinalSearchRequestStep<Client>
        implements ClientsByRoleSearchRequest {

    @Override
    public ClientsByRoleSearchRequest filter(ClientFilter value) {
        return this;
    }

    @Override
    public ClientsByRoleSearchRequest filter(Consumer<ClientFilter> fn) {
        return this;
    }

    @Override
    public ClientsByRoleSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public ClientsByRoleSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public ClientsByRoleSearchRequest sort(ClientSort value) {
        return this;
    }

    @Override
    public ClientsByRoleSearchRequest sort(Consumer<ClientSort> fn) {
        return this;
    }
}

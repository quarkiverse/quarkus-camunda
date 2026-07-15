package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.ClientFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.ClientsByGroupSearchRequest;
import io.camunda.client.api.search.response.Client;
import io.camunda.client.api.search.sort.ClientSort;

public class ClientsByGroupSearchRequest1Impl extends AbstractFinalSearchRequestStep<Client>
        implements ClientsByGroupSearchRequest {

    @Override
    public ClientsByGroupSearchRequest filter(ClientFilter value) {
        return this;
    }

    @Override
    public ClientsByGroupSearchRequest filter(Consumer<ClientFilter> fn) {
        return this;
    }

    @Override
    public ClientsByGroupSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public ClientsByGroupSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public ClientsByGroupSearchRequest sort(ClientSort value) {
        return this;
    }

    @Override
    public ClientsByGroupSearchRequest sort(Consumer<ClientSort> fn) {
        return this;
    }
}

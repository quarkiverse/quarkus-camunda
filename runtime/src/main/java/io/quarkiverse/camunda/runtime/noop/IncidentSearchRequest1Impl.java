package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.IncidentFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.IncidentSearchRequest;
import io.camunda.client.api.search.response.Incident;
import io.camunda.client.api.search.sort.IncidentSort;

public class IncidentSearchRequest1Impl extends AbstractFinalSearchRequestStep<Incident> implements IncidentSearchRequest {

    @Override
    public IncidentSearchRequest filter(IncidentFilter value) {
        return this;
    }

    @Override
    public IncidentSearchRequest filter(Consumer<IncidentFilter> fn) {
        return this;
    }

    @Override
    public IncidentSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public IncidentSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public IncidentSearchRequest sort(IncidentSort value) {
        return this;
    }

    @Override
    public IncidentSearchRequest sort(Consumer<IncidentSort> fn) {
        return this;
    }
}

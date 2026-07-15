package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.IncidentFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.IncidentsByProcessInstanceSearchRequest;
import io.camunda.client.api.search.response.Incident;
import io.camunda.client.api.search.sort.IncidentSort;

public class IncidentsByProcessInstanceSearchRequest1Impl extends AbstractFinalSearchRequestStep<Incident>
        implements IncidentsByProcessInstanceSearchRequest {

    @Override
    public IncidentsByProcessInstanceSearchRequest filter(IncidentFilter value) {
        return this;
    }

    @Override
    public IncidentsByProcessInstanceSearchRequest filter(Consumer<IncidentFilter> fn) {
        return this;
    }

    @Override
    public IncidentsByProcessInstanceSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public IncidentsByProcessInstanceSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public IncidentsByProcessInstanceSearchRequest sort(IncidentSort value) {
        return this;
    }

    @Override
    public IncidentsByProcessInstanceSearchRequest sort(Consumer<IncidentSort> fn) {
        return this;
    }
}

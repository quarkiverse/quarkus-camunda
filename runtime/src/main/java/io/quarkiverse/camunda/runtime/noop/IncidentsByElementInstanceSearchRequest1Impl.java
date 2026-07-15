package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.IncidentFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.IncidentsByElementInstanceSearchRequest;
import io.camunda.client.api.search.response.Incident;
import io.camunda.client.api.search.sort.IncidentSort;

public class IncidentsByElementInstanceSearchRequest1Impl extends AbstractFinalSearchRequestStep<Incident>
        implements IncidentsByElementInstanceSearchRequest {

    @Override
    public IncidentsByElementInstanceSearchRequest filter(IncidentFilter value) {
        return this;
    }

    @Override
    public IncidentsByElementInstanceSearchRequest filter(Consumer<IncidentFilter> fn) {
        return this;
    }

    @Override
    public IncidentsByElementInstanceSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public IncidentsByElementInstanceSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public IncidentsByElementInstanceSearchRequest sort(IncidentSort value) {
        return this;
    }

    @Override
    public IncidentsByElementInstanceSearchRequest sort(Consumer<IncidentSort> fn) {
        return this;
    }
}

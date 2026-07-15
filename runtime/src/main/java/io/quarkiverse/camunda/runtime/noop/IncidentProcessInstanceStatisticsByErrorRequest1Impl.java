package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.page.OffsetPage;
import io.camunda.client.api.statistics.request.IncidentProcessInstanceStatisticsByErrorRequest;
import io.camunda.client.api.statistics.response.IncidentProcessInstanceStatisticsByError;
import io.camunda.client.api.statistics.sort.IncidentProcessInstanceStatisticsByErrorSort;

public class IncidentProcessInstanceStatisticsByErrorRequest1Impl
        extends AbstractFinalSearchRequestStep<IncidentProcessInstanceStatisticsByError>
        implements IncidentProcessInstanceStatisticsByErrorRequest {
    @Override
    public IncidentProcessInstanceStatisticsByErrorRequest page(OffsetPage value) {
        return this;
    }

    @Override
    public IncidentProcessInstanceStatisticsByErrorRequest page(Consumer<OffsetPage> fn) {
        return this;
    }

    @Override
    public IncidentProcessInstanceStatisticsByErrorRequest sort(IncidentProcessInstanceStatisticsByErrorSort value) {
        return this;
    }

    @Override
    public IncidentProcessInstanceStatisticsByErrorRequest sort(Consumer<IncidentProcessInstanceStatisticsByErrorSort> fn) {
        return this;
    }
}

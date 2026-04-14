package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.page.OffsetPage;
import io.camunda.client.api.statistics.request.IncidentProcessInstanceStatisticsByDefinitionRequest;
import io.camunda.client.api.statistics.response.IncidentProcessInstanceStatisticsByDefinition;
import io.camunda.client.api.statistics.sort.IncidentProcessInstanceStatisticsByDefinitionSort;

public class IncidentProcessInstanceStatisticsByDefinitionRequest1Impl
        extends AbstractFinalSearchRequestStep<IncidentProcessInstanceStatisticsByDefinition>
        implements IncidentProcessInstanceStatisticsByDefinitionRequest {

    @Override
    public IncidentProcessInstanceStatisticsByDefinitionRequest page(OffsetPage value) {
        return this;
    }

    @Override
    public IncidentProcessInstanceStatisticsByDefinitionRequest page(Consumer<OffsetPage> fn) {
        return this;
    }

    @Override
    public IncidentProcessInstanceStatisticsByDefinitionRequest sort(IncidentProcessInstanceStatisticsByDefinitionSort value) {
        return this;
    }

    @Override
    public IncidentProcessInstanceStatisticsByDefinitionRequest sort(
            Consumer<IncidentProcessInstanceStatisticsByDefinitionSort> fn) {
        return this;
    }
}

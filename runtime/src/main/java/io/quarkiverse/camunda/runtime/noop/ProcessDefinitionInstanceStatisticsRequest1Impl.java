package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.page.OffsetPage;
import io.camunda.client.api.statistics.request.ProcessDefinitionInstanceStatisticsRequest;
import io.camunda.client.api.statistics.response.ProcessDefinitionInstanceStatistics;
import io.camunda.client.api.statistics.sort.ProcessDefinitionInstanceStatisticsSort;

public class ProcessDefinitionInstanceStatisticsRequest1Impl
        extends AbstractFinalSearchRequestStep<ProcessDefinitionInstanceStatistics>
        implements ProcessDefinitionInstanceStatisticsRequest {
    @Override
    public ProcessDefinitionInstanceStatisticsRequest page(OffsetPage value) {
        return this;
    }

    @Override
    public ProcessDefinitionInstanceStatisticsRequest page(Consumer<OffsetPage> fn) {
        return this;
    }

    @Override
    public ProcessDefinitionInstanceStatisticsRequest sort(ProcessDefinitionInstanceStatisticsSort value) {
        return this;
    }

    @Override
    public ProcessDefinitionInstanceStatisticsRequest sort(Consumer<ProcessDefinitionInstanceStatisticsSort> fn) {
        return this;
    }
}

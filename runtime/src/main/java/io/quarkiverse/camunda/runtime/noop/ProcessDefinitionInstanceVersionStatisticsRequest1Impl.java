package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.page.OffsetPage;
import io.camunda.client.api.statistics.filter.ProcessDefinitionInstanceVersionStatisticsFilter;
import io.camunda.client.api.statistics.request.ProcessDefinitionInstanceVersionStatisticsRequest;
import io.camunda.client.api.statistics.response.ProcessDefinitionInstanceVersionStatistics;
import io.camunda.client.api.statistics.sort.ProcessDefinitionInstanceVersionStatisticsSort;

public class ProcessDefinitionInstanceVersionStatisticsRequest1Impl
        extends AbstractFinalSearchRequestStep<ProcessDefinitionInstanceVersionStatistics>
        implements ProcessDefinitionInstanceVersionStatisticsRequest {
    @Override
    public ProcessDefinitionInstanceVersionStatisticsRequest page(OffsetPage value) {
        return this;
    }

    @Override
    public ProcessDefinitionInstanceVersionStatisticsRequest page(Consumer<OffsetPage> fn) {
        return this;
    }

    @Override
    public ProcessDefinitionInstanceVersionStatisticsRequest sort(ProcessDefinitionInstanceVersionStatisticsSort value) {
        return this;
    }

    @Override
    public ProcessDefinitionInstanceVersionStatisticsRequest sort(Consumer<ProcessDefinitionInstanceVersionStatisticsSort> fn) {
        return this;
    }

    @Override
    public ProcessDefinitionInstanceVersionStatisticsRequest filter(ProcessDefinitionInstanceVersionStatisticsFilter value) {
        return this;
    }

    @Override
    public ProcessDefinitionInstanceVersionStatisticsRequest filter(
            Consumer<ProcessDefinitionInstanceVersionStatisticsFilter> fn) {
        return this;
    }
}

package io.quarkiverse.camunda.runtime.noop;

import java.util.List;
import java.util.function.Consumer;

import io.camunda.client.api.statistics.filter.ProcessDefinitionStatisticsFilter;
import io.camunda.client.api.statistics.request.ProcessDefinitionElementStatisticsRequest;
import io.camunda.client.api.statistics.response.ProcessElementStatistics;

public class ProcessDefinitionElementStatisticsRequest1Impl extends AbstractStep<List<ProcessElementStatistics>>
        implements ProcessDefinitionElementStatisticsRequest {

    @Override
    public ProcessDefinitionElementStatisticsRequest filter(ProcessDefinitionStatisticsFilter value) {
        return this;
    }

    @Override
    public ProcessDefinitionElementStatisticsRequest filter(Consumer<ProcessDefinitionStatisticsFilter> fn) {
        return this;
    }
}

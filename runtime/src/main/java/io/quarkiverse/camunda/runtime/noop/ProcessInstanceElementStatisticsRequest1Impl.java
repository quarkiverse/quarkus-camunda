package io.quarkiverse.camunda.runtime.noop;

import java.util.List;

import io.camunda.client.api.statistics.request.ProcessInstanceElementStatisticsRequest;
import io.camunda.client.api.statistics.response.ProcessElementStatistics;

public class ProcessInstanceElementStatisticsRequest1Impl extends AbstractStep<List<ProcessElementStatistics>>
        implements ProcessInstanceElementStatisticsRequest {
}

package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.statistics.request.GlobalJobStatisticsRequest;
import io.camunda.client.api.statistics.response.GlobalJobStatistics;

public class GlobalJobStatisticsRequest1Impl extends AbstractStep<GlobalJobStatistics> implements GlobalJobStatisticsRequest {
    @Override
    public GlobalJobStatisticsRequest jobType(String jobType) {
        return this;
    }
}

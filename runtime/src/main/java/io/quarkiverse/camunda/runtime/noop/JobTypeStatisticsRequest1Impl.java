package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.page.CursorForwardPage;
import io.camunda.client.api.statistics.filter.JobTypeStatisticsFilter;
import io.camunda.client.api.statistics.request.JobTypeStatisticsRequest;
import io.camunda.client.api.statistics.response.JobTypeStatistics;

public class JobTypeStatisticsRequest1Impl extends AbstractStep<JobTypeStatistics> implements JobTypeStatisticsRequest {
    @Override
    public JobTypeStatisticsRequest filter(JobTypeStatisticsFilter value) {
        return this;
    }

    @Override
    public JobTypeStatisticsRequest filter(Consumer<JobTypeStatisticsFilter> fn) {
        return this;
    }

    @Override
    public JobTypeStatisticsRequest page(CursorForwardPage value) {
        return this;
    }

    @Override
    public JobTypeStatisticsRequest page(Consumer<CursorForwardPage> fn) {
        return this;
    }
}

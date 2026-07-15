package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.page.CursorForwardPage;
import io.camunda.client.api.statistics.filter.JobErrorStatisticsFilter;
import io.camunda.client.api.statistics.request.JobErrorStatisticsRequest;
import io.camunda.client.api.statistics.response.JobErrorStatistics;

public class JobErrorStatisticsRequest1Impl extends AbstractStep<JobErrorStatistics> implements JobErrorStatisticsRequest {
    @Override
    public JobErrorStatisticsRequest filter(JobErrorStatisticsFilter value) {
        return this;
    }

    @Override
    public JobErrorStatisticsRequest filter(Consumer<JobErrorStatisticsFilter> fn) {
        return this;
    }

    @Override
    public JobErrorStatisticsRequest page(CursorForwardPage value) {
        return this;
    }

    @Override
    public JobErrorStatisticsRequest page(Consumer<CursorForwardPage> fn) {
        return this;
    }
}

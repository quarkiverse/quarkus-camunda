package io.quarkiverse.camunda.runtime.noop;

import java.time.Duration;
import java.util.function.Consumer;

import io.camunda.client.api.search.page.CursorForwardPage;
import io.camunda.client.api.statistics.request.JobTimeSeriesStatisticsRequest;
import io.camunda.client.api.statistics.response.JobTimeSeriesStatistics;

public class JobTimeSeriesStatisticsRequest1Impl extends AbstractStep<JobTimeSeriesStatistics>
        implements JobTimeSeriesStatisticsRequest {
    @Override
    public JobTimeSeriesStatisticsRequest resolution(Duration resolution) {
        return this;
    }

    @Override
    public JobTimeSeriesStatisticsRequest page(CursorForwardPage value) {
        return this;
    }

    @Override
    public JobTimeSeriesStatisticsRequest page(Consumer<CursorForwardPage> fn) {
        return this;
    }
}

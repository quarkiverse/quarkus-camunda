package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.page.CursorForwardPage;
import io.camunda.client.api.statistics.request.JobWorkerStatisticsRequest;
import io.camunda.client.api.statistics.response.JobWorkerStatistics;

public class JobWorkerStatisticsRequest1Impl extends AbstractStep<JobWorkerStatistics> implements JobWorkerStatisticsRequest {
    @Override
    public JobWorkerStatisticsRequest page(CursorForwardPage value) {
        return this;
    }

    @Override
    public JobWorkerStatisticsRequest page(Consumer<CursorForwardPage> fn) {
        return this;
    }
}

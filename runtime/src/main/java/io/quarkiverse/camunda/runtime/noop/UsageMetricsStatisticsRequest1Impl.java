package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.statistics.request.UsageMetricsStatisticsRequest;
import io.camunda.client.api.statistics.response.UsageMetricsStatistics;

public class UsageMetricsStatisticsRequest1Impl extends AbstractStep<UsageMetricsStatistics>
        implements UsageMetricsStatisticsRequest {

    @Override
    public UsageMetricsStatisticsRequest withTenants(boolean withTenants) {
        return this;
    }

    @Override
    public UsageMetricsStatisticsRequest tenantId(String tenantId) {
        return this;
    }
}

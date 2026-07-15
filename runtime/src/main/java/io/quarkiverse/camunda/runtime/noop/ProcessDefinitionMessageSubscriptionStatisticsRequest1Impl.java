package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.MessageSubscriptionFilter;
import io.camunda.client.api.search.page.CursorForwardPage;
import io.camunda.client.api.statistics.request.ProcessDefinitionMessageSubscriptionStatisticsRequest;
import io.camunda.client.api.statistics.response.ProcessDefinitionMessageSubscriptionStatistics;

public class ProcessDefinitionMessageSubscriptionStatisticsRequest1Impl
        extends AbstractStep<ProcessDefinitionMessageSubscriptionStatistics>
        implements ProcessDefinitionMessageSubscriptionStatisticsRequest {

    @Override
    public ProcessDefinitionMessageSubscriptionStatisticsRequest filter(MessageSubscriptionFilter value) {
        return this;
    }

    @Override
    public ProcessDefinitionMessageSubscriptionStatisticsRequest filter(Consumer<MessageSubscriptionFilter> fn) {
        return this;
    }

    @Override
    public ProcessDefinitionMessageSubscriptionStatisticsRequest page(CursorForwardPage value) {
        return this;
    }

    @Override
    public ProcessDefinitionMessageSubscriptionStatisticsRequest page(Consumer<CursorForwardPage> fn) {
        return this;
    }
}

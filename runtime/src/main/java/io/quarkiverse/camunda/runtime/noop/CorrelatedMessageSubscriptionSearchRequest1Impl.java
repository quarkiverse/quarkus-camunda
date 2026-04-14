package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.CorrelatedMessageSubscriptionFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.CorrelatedMessageSubscriptionSearchRequest;
import io.camunda.client.api.search.response.CorrelatedMessageSubscription;
import io.camunda.client.api.search.sort.CorrelatedMessageSubscriptionSort;

public class CorrelatedMessageSubscriptionSearchRequest1Impl extends
        AbstractFinalSearchRequestStep<CorrelatedMessageSubscription> implements CorrelatedMessageSubscriptionSearchRequest {

    @Override
    public CorrelatedMessageSubscriptionSearchRequest filter(CorrelatedMessageSubscriptionFilter value) {
        return this;
    }

    @Override
    public CorrelatedMessageSubscriptionSearchRequest filter(Consumer<CorrelatedMessageSubscriptionFilter> fn) {
        return this;
    }

    @Override
    public CorrelatedMessageSubscriptionSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public CorrelatedMessageSubscriptionSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public CorrelatedMessageSubscriptionSearchRequest sort(CorrelatedMessageSubscriptionSort value) {
        return this;
    }

    @Override
    public CorrelatedMessageSubscriptionSearchRequest sort(Consumer<CorrelatedMessageSubscriptionSort> fn) {
        return this;
    }
}

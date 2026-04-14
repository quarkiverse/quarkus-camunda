package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.MessageSubscriptionFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.MessageSubscriptionSearchRequest;
import io.camunda.client.api.search.response.MessageSubscription;
import io.camunda.client.api.search.sort.MessageSubscriptionSort;

public class MessageSubscriptionSearchRequest1Impl extends AbstractFinalSearchRequestStep<MessageSubscription>
        implements MessageSubscriptionSearchRequest {

    @Override
    public MessageSubscriptionSearchRequest filter(MessageSubscriptionFilter value) {
        return this;
    }

    @Override
    public MessageSubscriptionSearchRequest filter(Consumer<MessageSubscriptionFilter> fn) {
        return this;
    }

    @Override
    public MessageSubscriptionSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public MessageSubscriptionSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public MessageSubscriptionSearchRequest sort(MessageSubscriptionSort value) {
        return this;
    }

    @Override
    public MessageSubscriptionSearchRequest sort(Consumer<MessageSubscriptionSort> fn) {
        return this;
    }
}

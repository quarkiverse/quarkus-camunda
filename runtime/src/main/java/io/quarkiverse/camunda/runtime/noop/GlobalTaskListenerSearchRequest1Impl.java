package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.GlobalTaskListenerFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.GlobalTaskListenerSearchRequest;
import io.camunda.client.api.search.response.GlobalTaskListener;
import io.camunda.client.api.search.sort.GlobalTaskListenerSort;

public class GlobalTaskListenerSearchRequest1Impl extends AbstractFinalSearchRequestStep<GlobalTaskListener>
        implements GlobalTaskListenerSearchRequest {

    @Override
    public GlobalTaskListenerSearchRequest filter(GlobalTaskListenerFilter value) {
        return this;
    }

    @Override
    public GlobalTaskListenerSearchRequest filter(Consumer<GlobalTaskListenerFilter> fn) {
        return this;
    }

    @Override
    public GlobalTaskListenerSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public GlobalTaskListenerSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public GlobalTaskListenerSearchRequest sort(GlobalTaskListenerSort value) {
        return this;
    }

    @Override
    public GlobalTaskListenerSearchRequest sort(Consumer<GlobalTaskListenerSort> fn) {
        return this;
    }
}

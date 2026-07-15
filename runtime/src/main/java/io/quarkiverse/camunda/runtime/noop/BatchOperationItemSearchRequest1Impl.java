package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.BatchOperationItemFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.BatchOperationItemSearchRequest;
import io.camunda.client.api.search.response.BatchOperationItems;
import io.camunda.client.api.search.sort.BatchOperationItemSort;

public class BatchOperationItemSearchRequest1Impl extends AbstractFinalSearchRequestStep<BatchOperationItems.BatchOperationItem>
        implements BatchOperationItemSearchRequest {

    @Override
    public BatchOperationItemSearchRequest filter(BatchOperationItemFilter value) {
        return this;
    }

    @Override
    public BatchOperationItemSearchRequest filter(Consumer<BatchOperationItemFilter> fn) {
        return this;
    }

    @Override
    public BatchOperationItemSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public BatchOperationItemSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public BatchOperationItemSearchRequest sort(BatchOperationItemSort value) {
        return this;
    }

    @Override
    public BatchOperationItemSearchRequest sort(Consumer<BatchOperationItemSort> fn) {
        return this;
    }
}

package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.BatchOperationFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.BatchOperationSearchRequest;
import io.camunda.client.api.search.response.BatchOperation;
import io.camunda.client.api.search.sort.BatchOperationSort;

public class BatchOperationSearchRequest1Impl extends AbstractFinalSearchRequestStep<BatchOperation>
        implements BatchOperationSearchRequest {

    @Override
    public BatchOperationSearchRequest filter(BatchOperationFilter value) {
        return this;
    }

    @Override
    public BatchOperationSearchRequest filter(Consumer<BatchOperationFilter> fn) {
        return this;
    }

    @Override
    public BatchOperationSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public BatchOperationSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public BatchOperationSearchRequest sort(BatchOperationSort value) {
        return this;
    }

    @Override
    public BatchOperationSearchRequest sort(Consumer<BatchOperationSort> fn) {
        return this;
    }
}

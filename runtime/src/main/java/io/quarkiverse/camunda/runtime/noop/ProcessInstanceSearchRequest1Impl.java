package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.ProcessInstanceFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.ProcessInstanceSearchRequest;
import io.camunda.client.api.search.response.ProcessInstance;
import io.camunda.client.api.search.sort.ProcessInstanceSort;

public class ProcessInstanceSearchRequest1Impl extends AbstractFinalSearchRequestStep<ProcessInstance>
        implements ProcessInstanceSearchRequest {

    @Override
    public ProcessInstanceSearchRequest filter(ProcessInstanceFilter value) {
        return this;
    }

    @Override
    public ProcessInstanceSearchRequest filter(Consumer<ProcessInstanceFilter> fn) {
        return this;
    }

    @Override
    public ProcessInstanceSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public ProcessInstanceSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public ProcessInstanceSearchRequest sort(ProcessInstanceSort value) {
        return this;
    }

    @Override
    public ProcessInstanceSearchRequest sort(Consumer<ProcessInstanceSort> fn) {
        return this;
    }
}

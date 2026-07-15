package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.ProcessDefinitionFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.ProcessDefinitionSearchRequest;
import io.camunda.client.api.search.response.ProcessDefinition;
import io.camunda.client.api.search.sort.ProcessDefinitionSort;

public class ProcessDefinitionSearchRequest1Impl extends AbstractFinalSearchRequestStep<ProcessDefinition>
        implements ProcessDefinitionSearchRequest {

    @Override
    public ProcessDefinitionSearchRequest filter(ProcessDefinitionFilter value) {
        return this;
    }

    @Override
    public ProcessDefinitionSearchRequest filter(Consumer<ProcessDefinitionFilter> fn) {
        return this;
    }

    @Override
    public ProcessDefinitionSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public ProcessDefinitionSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public ProcessDefinitionSearchRequest sort(ProcessDefinitionSort value) {
        return this;
    }

    @Override
    public ProcessDefinitionSearchRequest sort(Consumer<ProcessDefinitionSort> fn) {
        return this;
    }
}

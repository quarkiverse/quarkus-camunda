package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.ElementInstanceFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.ElementInstanceSearchRequest;
import io.camunda.client.api.search.response.ElementInstance;
import io.camunda.client.api.search.sort.ElementInstanceSort;

public class ElementInstanceSearchRequest1Impl extends AbstractFinalSearchRequestStep<ElementInstance>
        implements ElementInstanceSearchRequest {

    @Override
    public ElementInstanceSearchRequest filter(ElementInstanceFilter value) {
        return this;
    }

    @Override
    public ElementInstanceSearchRequest filter(Consumer<ElementInstanceFilter> fn) {
        return this;
    }

    @Override
    public ElementInstanceSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public ElementInstanceSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public ElementInstanceSearchRequest sort(ElementInstanceSort value) {
        return this;
    }

    @Override
    public ElementInstanceSearchRequest sort(Consumer<ElementInstanceSort> fn) {
        return this;
    }
}

package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.AuditLogFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.AuditLogSearchRequest;
import io.camunda.client.api.search.response.AuditLogResult;
import io.camunda.client.api.search.sort.AuditLogSort;

public class AuditLogSearchRequest1Impl extends AbstractFinalSearchRequestStep<AuditLogResult>
        implements AuditLogSearchRequest {
    @Override
    public AuditLogSearchRequest filter(AuditLogFilter value) {
        return this;
    }

    @Override
    public AuditLogSearchRequest filter(Consumer<AuditLogFilter> fn) {
        return this;
    }

    @Override
    public AuditLogSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public AuditLogSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public AuditLogSearchRequest sort(AuditLogSort value) {
        return this;
    }

    @Override
    public AuditLogSearchRequest sort(Consumer<AuditLogSort> fn) {
        return this;
    }
}

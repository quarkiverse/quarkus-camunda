package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.UserTaskAuditLogFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.UserTaskAuditLogSearchRequest;
import io.camunda.client.api.search.response.AuditLogResult;
import io.camunda.client.api.search.sort.AuditLogSort;

public class UserTaskAuditLogSearchRequest1Impl extends AbstractFinalSearchRequestStep<AuditLogResult>
        implements UserTaskAuditLogSearchRequest {
    @Override
    public UserTaskAuditLogSearchRequest filter(UserTaskAuditLogFilter value) {
        return this;
    }

    @Override
    public UserTaskAuditLogSearchRequest filter(Consumer<UserTaskAuditLogFilter> fn) {
        return this;
    }

    @Override
    public UserTaskAuditLogSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public UserTaskAuditLogSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public UserTaskAuditLogSearchRequest sort(AuditLogSort value) {
        return this;
    }

    @Override
    public UserTaskAuditLogSearchRequest sort(Consumer<AuditLogSort> fn) {
        return this;
    }
}

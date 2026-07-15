package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.RoleGroupFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.GroupsByRoleSearchRequest;
import io.camunda.client.api.search.response.RoleGroup;
import io.camunda.client.api.search.sort.RoleGroupSort;

public class GroupsByRoleSearchRequest1Impl extends AbstractFinalSearchRequestStep<RoleGroup>
        implements GroupsByRoleSearchRequest {

    @Override
    public GroupsByRoleSearchRequest filter(RoleGroupFilter value) {
        return this;
    }

    @Override
    public GroupsByRoleSearchRequest filter(Consumer<RoleGroupFilter> fn) {
        return this;
    }

    @Override
    public GroupsByRoleSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public GroupsByRoleSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public GroupsByRoleSearchRequest sort(RoleGroupSort value) {
        return this;
    }

    @Override
    public GroupsByRoleSearchRequest sort(Consumer<RoleGroupSort> fn) {
        return this;
    }
}

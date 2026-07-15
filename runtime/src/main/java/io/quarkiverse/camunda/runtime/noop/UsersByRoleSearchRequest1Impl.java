package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.RoleUserFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.UsersByRoleSearchRequest;
import io.camunda.client.api.search.response.RoleUser;
import io.camunda.client.api.search.sort.RoleUserSort;

public class UsersByRoleSearchRequest1Impl extends AbstractFinalSearchRequestStep<RoleUser>
        implements UsersByRoleSearchRequest {

    @Override
    public UsersByRoleSearchRequest filter(RoleUserFilter value) {
        return null;
    }

    @Override
    public UsersByRoleSearchRequest filter(Consumer<RoleUserFilter> fn) {
        return null;
    }

    @Override
    public UsersByRoleSearchRequest page(AnyPage value) {
        return null;
    }

    @Override
    public UsersByRoleSearchRequest page(Consumer<AnyPage> fn) {
        return null;
    }

    @Override
    public UsersByRoleSearchRequest sort(RoleUserSort value) {
        return null;
    }

    @Override
    public UsersByRoleSearchRequest sort(Consumer<RoleUserSort> fn) {
        return null;
    }
}

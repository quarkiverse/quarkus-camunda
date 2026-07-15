package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.GroupUserFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.UsersByGroupSearchRequest;
import io.camunda.client.api.search.response.GroupUser;
import io.camunda.client.api.search.sort.GroupUserSort;

public class UsersByGroupSearchRequest1Impl extends AbstractFinalSearchRequestStep<GroupUser>
        implements UsersByGroupSearchRequest {

    @Override
    public UsersByGroupSearchRequest filter(GroupUserFilter value) {
        return this;
    }

    @Override
    public UsersByGroupSearchRequest filter(Consumer<GroupUserFilter> fn) {
        return this;
    }

    @Override
    public UsersByGroupSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public UsersByGroupSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public UsersByGroupSearchRequest sort(GroupUserSort value) {
        return this;
    }

    @Override
    public UsersByGroupSearchRequest sort(Consumer<GroupUserSort> fn) {
        return this;
    }
}

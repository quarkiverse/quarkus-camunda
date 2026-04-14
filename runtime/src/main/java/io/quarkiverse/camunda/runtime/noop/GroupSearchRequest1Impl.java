package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.GroupFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.GroupsSearchRequest;
import io.camunda.client.api.search.response.Group;
import io.camunda.client.api.search.sort.GroupSort;

public class GroupSearchRequest1Impl extends AbstractFinalSearchRequestStep<Group> implements GroupsSearchRequest {

    @Override
    public GroupsSearchRequest filter(GroupFilter value) {
        return this;
    }

    @Override
    public GroupsSearchRequest filter(Consumer<GroupFilter> fn) {
        return this;
    }

    @Override
    public GroupsSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public GroupsSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public GroupsSearchRequest sort(GroupSort value) {
        return this;
    }

    @Override
    public GroupsSearchRequest sort(Consumer<GroupSort> fn) {
        return this;
    }
}

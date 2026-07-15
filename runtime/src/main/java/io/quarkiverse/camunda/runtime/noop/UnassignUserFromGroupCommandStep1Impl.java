package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.UnassignUserFromGroupCommandStep1;
import io.camunda.client.api.response.UnassignUserFromGroupResponse;

public class UnassignUserFromGroupCommandStep1Impl extends AbstractStep<UnassignUserFromGroupResponse>
        implements UnassignUserFromGroupCommandStep1, UnassignUserFromGroupCommandStep1.UnassignUserFromGroupCommandStep2,
        UnassignUserFromGroupCommandStep1.UnassignUserFromGroupCommandStep3 {

    @Override
    public UnassignUserFromGroupCommandStep2 username(String username) {
        return this;
    }

    @Override
    public UnassignUserFromGroupCommandStep3 groupId(String groupId) {
        return this;
    }
}

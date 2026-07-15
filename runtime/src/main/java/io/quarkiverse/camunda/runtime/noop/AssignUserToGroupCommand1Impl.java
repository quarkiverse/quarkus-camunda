package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.AssignUserToGroupCommandStep1;
import io.camunda.client.api.response.AssignUserToGroupResponse;

public class AssignUserToGroupCommand1Impl extends AbstractStep<AssignUserToGroupResponse>
        implements AssignUserToGroupCommandStep1, AssignUserToGroupCommandStep1.AssignUserToGroupCommandStep2,
        AssignUserToGroupCommandStep1.AssignUserToGroupCommandStep3 {

    @Override
    public AssignUserToGroupCommandStep2 username(String username) {
        return this;
    }

    @Override
    public AssignUserToGroupCommandStep3 groupId(String groupId) {
        return this;
    }
}

package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.AssignRoleToUserCommandStep1;
import io.camunda.client.api.response.AssignRoleToUserResponse;

public class AssignRoleToUserCommand1Impl extends AbstractStep<AssignRoleToUserResponse>
        implements AssignRoleToUserCommandStep1, AssignRoleToUserCommandStep1.AssignRoleToUserCommandStep2,
        AssignRoleToUserCommandStep1.AssignRoleToUserCommandStep3 {

    @Override
    public AssignRoleToUserCommandStep2 roleId(String roleId) {
        return this;
    }

    @Override
    public AssignRoleToUserCommandStep3 username(String username) {
        return this;
    }
}

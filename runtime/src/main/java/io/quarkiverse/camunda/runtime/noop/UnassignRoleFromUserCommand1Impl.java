package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.UnassignRoleFromUserCommandStep1;
import io.camunda.client.api.response.UnassignUserFromRoleResponse;

public class UnassignRoleFromUserCommand1Impl extends AbstractStep<UnassignUserFromRoleResponse>
        implements UnassignRoleFromUserCommandStep1, UnassignRoleFromUserCommandStep1.UnassignRoleFromUserCommandStep2,
        UnassignRoleFromUserCommandStep1.UnassignRoleFromUserCommandStep3 {

    @Override
    public UnassignRoleFromUserCommandStep2 roleId(String roleId) {
        return this;
    }

    @Override
    public UnassignRoleFromUserCommandStep3 username(String username) {
        return this;
    }
}

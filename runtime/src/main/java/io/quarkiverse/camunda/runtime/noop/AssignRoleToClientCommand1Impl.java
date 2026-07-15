package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.AssignRoleToClientCommandStep1;
import io.camunda.client.api.response.AssignRoleToClientResponse;

public class AssignRoleToClientCommand1Impl extends AbstractStep<AssignRoleToClientResponse>
        implements AssignRoleToClientCommandStep1, AssignRoleToClientCommandStep1.AssignRoleToClientCommandStep2,
        AssignRoleToClientCommandStep1.AssignRoleToClientCommandStep3 {

    @Override
    public AssignRoleToClientCommandStep2 roleId(String roleId) {
        return this;
    }

    @Override
    public AssignRoleToClientCommandStep3 clientId(String clientId) {
        return this;
    }
}

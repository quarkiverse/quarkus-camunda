package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.AssignRoleToGroupCommandStep1;
import io.camunda.client.api.response.AssignRoleToGroupResponse;

public class AssignRoleToGroupCommand1Impl extends AbstractStep<AssignRoleToGroupResponse>
        implements AssignRoleToGroupCommandStep1, AssignRoleToGroupCommandStep1.AssignRoleToGroupCommandStep2,
        AssignRoleToGroupCommandStep1.AssignRoleToGroupCommandStep3 {

    @Override
    public AssignRoleToGroupCommandStep2 roleId(String roleId) {
        return this;
    }

    @Override
    public AssignRoleToGroupCommandStep3 groupId(String groupId) {
        return this;
    }
}

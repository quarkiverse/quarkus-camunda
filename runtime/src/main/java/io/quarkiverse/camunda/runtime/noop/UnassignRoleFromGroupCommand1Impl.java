package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.UnassignRoleFromGroupCommandStep1;
import io.camunda.client.api.response.UnassignRoleFromGroupResponse;

public class UnassignRoleFromGroupCommand1Impl extends AbstractStep<UnassignRoleFromGroupResponse>
        implements UnassignRoleFromGroupCommandStep1, UnassignRoleFromGroupCommandStep1.UnassignRoleFromGroupCommandStep2,
        UnassignRoleFromGroupCommandStep1.UnassignRoleFromGroupCommandStep3 {

    @Override
    public UnassignRoleFromGroupCommandStep2 roleId(String roleId) {
        return this;
    }

    @Override
    public UnassignRoleFromGroupCommandStep3 groupId(String groupId) {
        return this;
    }
}

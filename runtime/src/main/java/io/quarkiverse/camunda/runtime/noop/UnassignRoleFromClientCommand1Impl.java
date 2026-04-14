package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.UnassignRoleFromClientCommandStep1;
import io.camunda.client.api.response.UnassignRoleFromClientResponse;

public class UnassignRoleFromClientCommand1Impl extends AbstractStep<UnassignRoleFromClientResponse>
        implements UnassignRoleFromClientCommandStep1, UnassignRoleFromClientCommandStep1.UnassignRoleFromClientCommandStep2,
        UnassignRoleFromClientCommandStep1.UnassignRoleFromClientCommandStep3 {

    @Override
    public UnassignRoleFromClientCommandStep2 roleId(String roleId) {
        return this;
    }

    @Override
    public UnassignRoleFromClientCommandStep3 clientId(String clientId) {
        return this;
    }
}

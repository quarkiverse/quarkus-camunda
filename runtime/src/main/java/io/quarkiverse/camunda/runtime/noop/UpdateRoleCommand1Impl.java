package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.UpdateRoleCommandStep1;
import io.camunda.client.api.response.UpdateRoleResponse;

public class UpdateRoleCommand1Impl extends AbstractStep<UpdateRoleResponse> implements UpdateRoleCommandStep1 {

    @Override
    public UpdateRoleCommandStep1 name(String name) {
        return this;
    }

    @Override
    public UpdateRoleCommandStep1 description(String description) {
        return this;
    }
}

package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.CreateRoleCommandStep1;
import io.camunda.client.api.response.CreateRoleResponse;

public class CreateRoleCommand1Impl extends AbstractStep<CreateRoleResponse>
        implements CreateRoleCommandStep1, CreateRoleCommandStep1.CreateRoleCommandStep2 {

    @Override
    public CreateRoleCommandStep2 roleId(String roleId) {
        return this;
    }

    @Override
    public CreateRoleCommandStep2 name(String name) {
        return this;
    }

    @Override
    public CreateRoleCommandStep2 description(String description) {
        return this;
    }
}

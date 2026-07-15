package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.CreateGroupCommandStep1;
import io.camunda.client.api.response.CreateGroupResponse;

public class CreateGroupCommand1Impl extends AbstractStep<CreateGroupResponse>
        implements CreateGroupCommandStep1, CreateGroupCommandStep1.CreateGroupCommandStep2 {

    @Override
    public CreateGroupCommandStep2 name(String name) {
        return this;
    }

    @Override
    public CreateGroupCommandStep2 description(String description) {
        return this;
    }

    @Override
    public CreateGroupCommandStep2 groupId(String groupId) {
        return this;
    }
}

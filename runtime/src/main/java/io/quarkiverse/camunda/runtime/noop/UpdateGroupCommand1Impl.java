package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.UpdateGroupCommandStep1;
import io.camunda.client.api.response.UpdateGroupResponse;

public class UpdateGroupCommand1Impl extends AbstractStep<UpdateGroupResponse> implements UpdateGroupCommandStep1 {

    @Override
    public UpdateGroupCommandStep1 name(String name) {
        return this;
    }

    @Override
    public UpdateGroupCommandStep1 description(String description) {
        return this;
    }
}

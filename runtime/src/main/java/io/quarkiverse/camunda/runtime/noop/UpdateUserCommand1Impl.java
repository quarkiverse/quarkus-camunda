package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.UpdateUserCommandStep1;
import io.camunda.client.api.response.UpdateUserResponse;

public class UpdateUserCommand1Impl extends AbstractStep<UpdateUserResponse> implements UpdateUserCommandStep1 {

    @Override
    public UpdateUserCommandStep1 name(String name) {
        return this;
    }

    @Override
    public UpdateUserCommandStep1 email(String email) {
        return this;
    }

    @Override
    public UpdateUserCommandStep1 password(String password) {
        return this;
    }
}

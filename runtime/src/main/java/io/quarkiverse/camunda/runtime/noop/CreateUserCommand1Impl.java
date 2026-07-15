package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.CreateUserCommandStep1;
import io.camunda.client.api.response.CreateUserResponse;

public class CreateUserCommand1Impl extends AbstractStep<CreateUserResponse> implements CreateUserCommandStep1 {

    @Override
    public CreateUserCommandStep1 username(String username) {
        return this;
    }

    @Override
    public CreateUserCommandStep1 email(String email) {
        return this;
    }

    @Override
    public CreateUserCommandStep1 name(String name) {
        return this;
    }

    @Override
    public CreateUserCommandStep1 password(String password) {
        return this;
    }
}

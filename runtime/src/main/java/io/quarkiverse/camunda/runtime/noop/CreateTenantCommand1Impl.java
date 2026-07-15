package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.CreateTenantCommandStep1;
import io.camunda.client.api.response.CreateTenantResponse;

public class CreateTenantCommand1Impl extends AbstractStep<CreateTenantResponse> implements CreateTenantCommandStep1 {

    @Override
    public CreateTenantCommandStep1 tenantId(String tenantId) {
        return this;
    }

    @Override
    public CreateTenantCommandStep1 name(String name) {
        return this;
    }

    @Override
    public CreateTenantCommandStep1 description(String description) {
        return this;
    }
}

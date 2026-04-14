package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.UpdateTenantCommandStep1;
import io.camunda.client.api.response.UpdateTenantResponse;

public class UpdateTenantCommand1Impl extends AbstractStep<UpdateTenantResponse> implements UpdateTenantCommandStep1 {

    @Override
    public UpdateTenantCommandStep1 name(String name) {
        return this;
    }

    @Override
    public UpdateTenantCommandStep1 description(String description) {
        return this;
    }
}

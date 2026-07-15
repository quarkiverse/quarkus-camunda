package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.CreateAuthorizationCommandStep1;
import io.camunda.client.api.response.CreateAuthorizationResponse;
import io.camunda.client.api.search.enums.OwnerType;
import io.camunda.client.api.search.enums.PermissionType;
import io.camunda.client.api.search.enums.ResourceType;

public class CreateAuthorizationCommand1Impl extends AbstractStep<CreateAuthorizationResponse>
        implements CreateAuthorizationCommandStep1, CreateAuthorizationCommandStep1.CreateAuthorizationCommandStep2,
        CreateAuthorizationCommandStep1.CreateAuthorizationCommandStep3,
        CreateAuthorizationCommandStep1.CreateAuthorizationCommandStep4,
        CreateAuthorizationCommandStep1.CreateAuthorizationCommandStep5,
        CreateAuthorizationCommandStep1.CreateAuthorizationCommandStep6 {

    @Override
    public CreateAuthorizationCommandStep2 ownerId(String ownerId) {
        return this;
    }

    @Override
    public CreateAuthorizationCommandStep3 ownerType(OwnerType ownerType) {
        return this;
    }

    @Override
    public CreateAuthorizationCommandStep4 resourceId(String resourceId) {
        return this;
    }

    @Override
    public CreateAuthorizationCommandStep4 resourcePropertyName(String resourcePropertyName) {
        return this;
    }

    @Override
    public CreateAuthorizationCommandStep5 resourceType(ResourceType resourceType) {
        return this;
    }

    @Override
    public CreateAuthorizationCommandStep6 permissionTypes(PermissionType... permissionTypes) {
        return this;
    }
}

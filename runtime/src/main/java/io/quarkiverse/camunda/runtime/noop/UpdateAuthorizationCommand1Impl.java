package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.UpdateAuthorizationCommandStep1;
import io.camunda.client.api.response.UpdateAuthorizationResponse;
import io.camunda.client.api.search.enums.OwnerType;
import io.camunda.client.api.search.enums.PermissionType;
import io.camunda.client.api.search.enums.ResourceType;

public class UpdateAuthorizationCommand1Impl extends AbstractStep<UpdateAuthorizationResponse>
        implements UpdateAuthorizationCommandStep1, UpdateAuthorizationCommandStep1.UpdateAuthorizationCommandStep2,
        UpdateAuthorizationCommandStep1.UpdateAuthorizationCommandStep3,
        UpdateAuthorizationCommandStep1.UpdateAuthorizationCommandStep4,
        UpdateAuthorizationCommandStep1.UpdateAuthorizationCommandStep5,
        UpdateAuthorizationCommandStep1.UpdateAuthorizationCommandStep6 {

    @Override
    public UpdateAuthorizationCommandStep2 ownerId(String ownerId) {
        return this;
    }

    @Override
    public UpdateAuthorizationCommandStep3 ownerType(OwnerType ownerType) {
        return this;
    }

    @Override
    public UpdateAuthorizationCommandStep4 resourceId(String resourceId) {
        return this;
    }

    @Override
    public UpdateAuthorizationCommandStep4 resourcePropertyName(String resourcePropertyName) {
        return this;
    }

    @Override
    public UpdateAuthorizationCommandStep5 resourceType(ResourceType resourceType) {
        return this;
    }

    @Override
    public UpdateAuthorizationCommandStep6 permissionTypes(PermissionType... permissionTypes) {
        return this;
    }
}

package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.fetch.TenantScopedClusterVariableGetRequest;
import io.camunda.client.api.search.response.ClusterVariable;

public class TenantScopedClusterVariableGetRequest1Impl extends AbstractStep<ClusterVariable>
        implements TenantScopedClusterVariableGetRequest {

    @Override
    public TenantScopedClusterVariableGetRequest withName(String name) {
        return this;
    }
}

package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.fetch.GloballyScopedClusterVariableGetRequest;
import io.camunda.client.api.search.response.ClusterVariable;

public class GloballyScopedClusterVariableGetRequest1Impl extends AbstractStep<ClusterVariable>
        implements GloballyScopedClusterVariableGetRequest {

    @Override
    public GloballyScopedClusterVariableGetRequest withName(String name) {
        return this;
    }
}

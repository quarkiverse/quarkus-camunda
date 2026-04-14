package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.ResolveIncidentCommandStep1;
import io.camunda.client.api.response.ResolveIncidentResponse;

public class ResolveIncidentCommandStep1Impl extends AbstractStep<ResolveIncidentResponse>
        implements ResolveIncidentCommandStep1 {

    @Override
    protected ResolveIncidentResponse create() {
        return new ResolveIncidentResponse() {
        };
    }

    @Override
    public ResolveIncidentCommandStep1 useRest() {
        return this;
    }

    @Override
    public ResolveIncidentCommandStep1 useGrpc() {
        return this;
    }

    @Override
    public ResolveIncidentCommandStep1 operationReference(long operationReference) {
        return this;
    }
}

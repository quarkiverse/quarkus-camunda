package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.UpdateRetriesJobCommandStep1;
import io.camunda.client.api.response.UpdateRetriesJobResponse;

public class UpdateRetriesJobCommandStep1Impl extends AbstractStep<UpdateRetriesJobResponse>
        implements UpdateRetriesJobCommandStep1, UpdateRetriesJobCommandStep1.UpdateRetriesJobCommandStep2 {

    @Override
    public UpdateRetriesJobCommandStep2 retries(int retries) {
        return this;
    }

    @Override
    protected UpdateRetriesJobResponse create() {
        return new UpdateRetriesJobResponse() {
        };
    }

    @Override
    public UpdateRetriesJobCommandStep1 useRest() {
        return this;
    }

    @Override
    public UpdateRetriesJobCommandStep1 useGrpc() {
        return this;
    }

    @Override
    public UpdateRetriesJobCommandStep2 operationReference(long operationReference) {
        return this;
    }
}

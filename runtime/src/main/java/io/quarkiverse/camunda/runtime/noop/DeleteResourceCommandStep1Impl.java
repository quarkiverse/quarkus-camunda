package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.DeleteResourceCommandStep1;
import io.camunda.client.api.response.DeleteResourceResponse;

public class DeleteResourceCommandStep1Impl extends AbstractStep<DeleteResourceResponse> implements DeleteResourceCommandStep1 {

    @Override
    public DeleteResourceCommandStep1 deleteHistory(boolean deleteHistory) {
        return this;
    }

    @Override
    public DeleteResourceCommandStep1 useRest() {
        return this;
    }

    @Override
    public DeleteResourceCommandStep1 useGrpc() {
        return this;
    }

    @Override
    public DeleteResourceCommandStep1 operationReference(long operationReference) {
        return this;
    }
}

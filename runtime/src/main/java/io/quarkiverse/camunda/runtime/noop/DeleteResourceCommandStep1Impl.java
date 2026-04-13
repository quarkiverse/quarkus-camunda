package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.DeleteResourceCommandStep1;
import io.camunda.client.api.response.DeleteResourceResponse;

public class DeleteResourceCommandStep1Impl extends AbstractStep<DeleteResourceResponse> implements DeleteResourceCommandStep1 {

    @Override
    protected DeleteResourceResponse create() {
        return new DeleteResourceResponse() {
        };
    }
}

package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.UnassignUserTaskCommandStep1;
import io.camunda.client.api.response.UnassignUserTaskResponse;

public class UnassignUserTaskCommandStep1Impl extends AbstractStep<UnassignUserTaskResponse>
        implements UnassignUserTaskCommandStep1 {

    @Override
    protected UnassignUserTaskResponse create() {
        return new UnassignUserTaskResponse() {
        };
    }
}

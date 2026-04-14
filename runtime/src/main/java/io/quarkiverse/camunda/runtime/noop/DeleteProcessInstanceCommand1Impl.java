package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.DeleteProcessInstanceCommandStep1;
import io.camunda.client.api.response.DeleteProcessInstanceResponse;

public class DeleteProcessInstanceCommand1Impl extends AbstractStep<DeleteProcessInstanceResponse>
        implements DeleteProcessInstanceCommandStep1 {

    @Override
    public DeleteProcessInstanceCommandStep1 operationReference(long operationReference) {
        return this;
    }
}

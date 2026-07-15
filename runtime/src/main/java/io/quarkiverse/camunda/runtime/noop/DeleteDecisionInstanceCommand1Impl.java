package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.DeleteDecisionInstanceCommandStep1;
import io.camunda.client.api.response.DeleteDecisionInstanceResponse;

public class DeleteDecisionInstanceCommand1Impl extends AbstractStep<DeleteDecisionInstanceResponse>
        implements DeleteDecisionInstanceCommandStep1 {

    @Override
    public DeleteDecisionInstanceCommandStep1 operationReference(long operationReference) {
        return this;
    }
}

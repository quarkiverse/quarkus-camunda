package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.DeleteDocumentCommandStep1;
import io.camunda.client.api.response.DeleteDocumentResponse;

public class DeleteDocumentCommand1Impl extends AbstractStep<DeleteDocumentResponse> implements DeleteDocumentCommandStep1 {

    @Override
    public DeleteDocumentCommandStep1 storeId(String storeId) {
        return this;
    }
}

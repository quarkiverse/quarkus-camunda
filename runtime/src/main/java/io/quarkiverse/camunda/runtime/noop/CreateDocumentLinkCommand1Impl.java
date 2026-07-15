package io.quarkiverse.camunda.runtime.noop;

import java.time.Duration;

import io.camunda.client.api.command.CreateDocumentLinkCommandStep1;
import io.camunda.client.api.response.DocumentLinkResponse;

public class CreateDocumentLinkCommand1Impl extends AbstractStep<DocumentLinkResponse>
        implements CreateDocumentLinkCommandStep1 {

    @Override
    public CreateDocumentLinkCommandStep1 storeId(String storeId) {
        return this;
    }

    @Override
    public CreateDocumentLinkCommandStep1 timeToLive(Duration timeToLive) {
        return this;
    }

    @Override
    public CreateDocumentLinkCommandStep1 contentHash(String contentHash) {
        return this;
    }
}

package io.quarkiverse.camunda.runtime.noop;

import java.io.InputStream;
import java.time.Duration;
import java.util.Map;

import io.camunda.client.api.command.CreateDocumentCommandStep1;
import io.camunda.client.api.response.DocumentReferenceResponse;

public class CreateDocumentCommand1Impl extends AbstractStep<DocumentReferenceResponse>
        implements CreateDocumentCommandStep1, CreateDocumentCommandStep1.CreateDocumentCommandStep2 {

    @Override
    public CreateDocumentCommandStep2 content(InputStream content) {
        return this;
    }

    @Override
    public CreateDocumentCommandStep2 content(byte[] content) {
        return this;
    }

    @Override
    public CreateDocumentCommandStep2 content(String content) {
        return this;
    }

    @Override
    public CreateDocumentCommandStep2 documentId(String documentId) {
        return this;
    }

    @Override
    public CreateDocumentCommandStep2 storeId(String storeId) {
        return this;
    }

    @Override
    public CreateDocumentCommandStep2 contentType(String contentType) {
        return this;
    }

    @Override
    public CreateDocumentCommandStep2 fileName(String name) {
        return this;
    }

    @Override
    public CreateDocumentCommandStep2 timeToLive(Duration timeToLive) {
        return this;
    }

    @Override
    public CreateDocumentCommandStep2 customMetadata(String key, Object value) {
        return this;
    }

    @Override
    public CreateDocumentCommandStep2 customMetadata(Map<String, Object> customMetadata) {
        return this;
    }

    @Override
    public CreateDocumentCommandStep2 processDefinitionId(String processDefinitionId) {
        return this;
    }

    @Override
    public CreateDocumentCommandStep2 processInstanceKey(long processInstanceKey) {
        return this;
    }
}

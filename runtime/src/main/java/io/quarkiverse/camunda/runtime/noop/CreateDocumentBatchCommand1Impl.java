package io.quarkiverse.camunda.runtime.noop;

import java.io.InputStream;
import java.time.Duration;
import java.util.Map;

import io.camunda.client.api.command.CreateDocumentBatchCommandStep1;
import io.camunda.client.api.response.DocumentReferenceBatchResponse;

public class CreateDocumentBatchCommand1Impl extends AbstractStep<DocumentReferenceBatchResponse>
        implements CreateDocumentBatchCommandStep1, CreateDocumentBatchCommandStep1.CreateDocumentBatchCommandStep2 {

    @Override
    public CreateDocumentBatchCommandStep2 content(InputStream content) {
        return this;
    }

    @Override
    public CreateDocumentBatchCommandStep2 content(byte[] content) {
        return this;
    }

    @Override
    public CreateDocumentBatchCommandStep2 content(String content) {
        return this;
    }

    @Override
    public CreateDocumentBatchCommandStep2 contentType(String contentType) {
        return this;
    }

    @Override
    public CreateDocumentBatchCommandStep2 fileName(String name) {
        return this;
    }

    @Override
    public CreateDocumentBatchCommandStep2 timeToLive(Duration timeToLive) {
        return this;
    }

    @Override
    public CreateDocumentBatchCommandStep2 customMetadata(String key, Object value) {
        return this;
    }

    @Override
    public CreateDocumentBatchCommandStep2 customMetadata(Map<String, Object> customMetadata) {
        return this;
    }

    @Override
    public CreateDocumentBatchCommandStep1 done() {
        return this;
    }

    @Override
    public CreateDocumentBatchCommandStep1 storeId(String storeId) {
        return this;
    }

    @Override
    public CreateDocumentBatchCommandStep1 processDefinitionId(String processDefinitionId) {
        return this;
    }

    @Override
    public CreateDocumentBatchCommandStep1 processInstanceKey(long processInstanceKey) {
        return this;
    }

    @Override
    public CreateDocumentBatchCommandStep2 addDocument() {
        return this;
    }
}

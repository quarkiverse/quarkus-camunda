package io.quarkiverse.camunda.runtime.noop;

import java.io.InputStream;

import io.camunda.client.api.fetch.DocumentContentGetRequest;

public class DocumentContentGetRequest1Impl extends AbstractStep<InputStream> implements DocumentContentGetRequest {

    @Override
    public DocumentContentGetRequest storeId(String storeId) {
        return this;
    }

    @Override
    public DocumentContentGetRequest contentHash(String contentHash) {
        return this;
    }
}

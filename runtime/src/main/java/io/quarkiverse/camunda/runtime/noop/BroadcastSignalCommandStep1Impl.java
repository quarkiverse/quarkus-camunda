package io.quarkiverse.camunda.runtime.noop;

import java.io.InputStream;
import java.util.Map;

import io.camunda.client.api.command.BroadcastSignalCommandStep1;
import io.camunda.client.api.response.BroadcastSignalResponse;
import io.camunda.client.impl.response.BroadcastSignalResponseImpl;
import io.camunda.zeebe.gateway.protocol.GatewayOuterClass;

public class BroadcastSignalCommandStep1Impl extends AbstractStep<BroadcastSignalResponse>
        implements BroadcastSignalCommandStep1.BroadcastSignalCommandStep2, BroadcastSignalCommandStep1 {
    @Override
    public BroadcastSignalCommandStep2 signalName(String signalName) {
        return this;
    }

    @Override
    public BroadcastSignalCommandStep2 variables(InputStream variables) {
        return this;
    }

    @Override
    public BroadcastSignalCommandStep2 variables(String variables) {
        return this;
    }

    @Override
    public BroadcastSignalCommandStep2 variables(Map<String, Object> variables) {
        return this;
    }

    @Override
    public BroadcastSignalCommandStep2 variables(Object variables) {
        return this;
    }

    @Override
    public BroadcastSignalCommandStep2 variable(String key, Object value) {
        return this;
    }

    @Override
    public BroadcastSignalCommandStep2 tenantId(String tenantId) {
        return this;
    }

    @Override
    protected BroadcastSignalResponse create() {
        return new BroadcastSignalResponseImpl(GatewayOuterClass.BroadcastSignalResponse.getDefaultInstance());
    }

}

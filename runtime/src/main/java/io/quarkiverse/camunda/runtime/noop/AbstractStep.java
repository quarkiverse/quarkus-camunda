package io.quarkiverse.camunda.runtime.noop;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import io.camunda.client.api.CamundaFuture;
import io.camunda.client.api.command.FinalCommandStep;
import io.camunda.client.api.response.ModifyProcessInstanceResponse;
import io.camunda.client.api.response.Topology;
import io.camunda.client.impl.response.ModifyProcessInstanceResponseImpl;
import io.camunda.client.impl.response.TopologyImpl;
import io.camunda.zeebe.gateway.protocol.GatewayOuterClass;

public class AbstractStep<T> {

    private static final Map<Class<?>, Object> DATA = new HashMap<>();
    static {
        DATA.put(ModifyProcessInstanceResponse.class,
                new ModifyProcessInstanceResponseImpl(GatewayOuterClass.ModifyProcessInstanceResponse.getDefaultInstance()));
        DATA.put(Topology.class, new TopologyImpl(GatewayOuterClass.TopologyResponse.getDefaultInstance()));
    }

    protected T create() {
        return null;
    }

    protected CamundaFuture<T> createFuture() {
        return new FutureImpl<>(create());
    }

    protected FinalCommandStep<T> createFinal() {
        return new FinalCommandStepImpl<>(create());
    }

    public CamundaFuture<T> send() {
        return createFuture();
    }

    public FinalCommandStep<T> requestTimeout(Duration requestTimeout) {
        return createFinal();
    }

}

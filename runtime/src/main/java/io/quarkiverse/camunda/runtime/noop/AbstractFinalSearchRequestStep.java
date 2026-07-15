package io.quarkiverse.camunda.runtime.noop;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import io.camunda.client.api.CamundaFuture;
import io.camunda.client.api.response.ModifyProcessInstanceResponse;
import io.camunda.client.api.response.Topology;
import io.camunda.client.api.search.request.FinalSearchRequestStep;
import io.camunda.client.api.search.response.SearchResponse;
import io.camunda.client.impl.response.ModifyProcessInstanceResponseImpl;
import io.camunda.client.impl.response.TopologyImpl;
import io.camunda.zeebe.gateway.protocol.GatewayOuterClass;

public class AbstractFinalSearchRequestStep<T> implements FinalSearchRequestStep<T> {

    private static final Map<Class<?>, Object> DATA = new HashMap<>();
    static {
        DATA.put(ModifyProcessInstanceResponse.class,
                new ModifyProcessInstanceResponseImpl(GatewayOuterClass.ModifyProcessInstanceResponse.getDefaultInstance()));
        DATA.put(Topology.class, new TopologyImpl(GatewayOuterClass.TopologyResponse.getDefaultInstance()));
    }

    protected SearchResponse<T> create() {
        return null;
    }

    protected CamundaFuture<SearchResponse<T>> createFuture() {
        return new FutureImpl<>(create());
    }

    protected FinalSearchRequestStep<T> createFinal() {
        return new FinalSearchRequstStep1Impl<>();
    }

    @Override
    public FinalSearchRequestStep<T> requestTimeout(Duration requestTimeout) {
        return createFinal();
    }

    @Override
    public CamundaFuture<SearchResponse<T>> send() {
        return createFuture();
    }
}

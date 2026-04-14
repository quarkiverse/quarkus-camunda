package io.quarkiverse.camunda.runtime.noop;

import java.io.InputStream;
import java.util.Map;

import io.camunda.client.api.command.EvaluateConditionalCommandStep1;
import io.camunda.client.api.response.EvaluateConditionalResponse;

public class EvaluateConditionalCommandStep1Impl extends AbstractStep<EvaluateConditionalResponse>
        implements EvaluateConditionalCommandStep1, EvaluateConditionalCommandStep1.EvaluateConditionalCommandStep2 {
    @Override
    public EvaluateConditionalCommandStep1 useRest() {
        return this;
    }

    @Override
    public EvaluateConditionalCommandStep1 useGrpc() {
        return this;
    }

    @Override
    public EvaluateConditionalCommandStep2 variables(String variables) {
        return this;
    }

    @Override
    public EvaluateConditionalCommandStep2 variables(Object variables) {
        return this;
    }

    @Override
    public EvaluateConditionalCommandStep2 variables(InputStream variables) {
        return this;
    }

    @Override
    public EvaluateConditionalCommandStep2 variables(Map<String, Object> variables) {
        return this;
    }

    @Override
    public EvaluateConditionalCommandStep2 variable(String key, Object value) {
        return this;
    }

    @Override
    public EvaluateConditionalCommandStep2 processDefinitionKey(long processDefinitionKey) {
        return this;
    }

    @Override
    public EvaluateConditionalCommandStep2 tenantId(String tenantId) {
        return this;
    }
}

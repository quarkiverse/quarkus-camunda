package io.quarkiverse.camunda.runtime.noop;

import java.io.InputStream;
import java.util.Map;

import io.camunda.client.api.command.EvaluateExpressionCommandStep1;
import io.camunda.client.api.response.EvaluateExpressionResponse;

public class EvaluateExpressionCommand1Impl extends AbstractStep<EvaluateExpressionResponse>
        implements EvaluateExpressionCommandStep1, EvaluateExpressionCommandStep1.EvaluateExpressionCommandStep2 {
    @Override
    public EvaluateExpressionCommandStep2 expression(String expression) {
        return this;
    }

    @Override
    public EvaluateExpressionCommandStep2 variables(String variables) {
        return this;
    }

    @Override
    public EvaluateExpressionCommandStep2 variables(Object variables) {
        return this;
    }

    @Override
    public EvaluateExpressionCommandStep2 variables(InputStream variables) {
        return this;
    }

    @Override
    public EvaluateExpressionCommandStep2 variables(Map<String, Object> variables) {
        return this;
    }

    @Override
    public EvaluateExpressionCommandStep2 variable(String key, Object value) {
        return this;
    }

    @Override
    public EvaluateExpressionCommandStep2 tenantId(String tenantId) {
        return this;
    }
}

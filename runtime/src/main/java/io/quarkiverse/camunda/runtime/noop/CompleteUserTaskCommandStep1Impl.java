package io.quarkiverse.camunda.runtime.noop;

import java.io.InputStream;
import java.util.Map;

import io.camunda.client.api.command.CompleteUserTaskCommandStep1;
import io.camunda.client.api.response.CompleteUserTaskResponse;

public class CompleteUserTaskCommandStep1Impl extends AbstractStep<CompleteUserTaskResponse>
        implements CompleteUserTaskCommandStep1 {

    @Override
    public CompleteUserTaskCommandStep1 action(String action) {
        return this;
    }

    @Override
    public CompleteUserTaskCommandStep1 variables(String variables) {
        return this;
    }

    @Override
    public CompleteUserTaskCommandStep1 variables(Object variables) {
        return this;
    }

    @Override
    public CompleteUserTaskCommandStep1 variables(InputStream variables) {
        return this;
    }

    @Override
    public CompleteUserTaskCommandStep1 variables(Map<String, Object> variables) {
        return this;
    }

    @Override
    public CompleteUserTaskCommandStep1 variable(String key, Object value) {
        return this;
    }

    @Override
    protected CompleteUserTaskResponse create() {
        return new CompleteUserTaskResponse() {
        };
    }
}

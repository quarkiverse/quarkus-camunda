package io.quarkiverse.camunda.runtime.noop;

import java.util.List;

import io.camunda.client.api.command.CreateGlobalTaskListenerCommandStep1;
import io.camunda.client.api.response.GlobalTaskListenerResponse;
import io.camunda.client.api.search.enums.GlobalTaskListenerEventType;

public class CreateGlobalTaskListenerCommand1Impl extends AbstractStep<GlobalTaskListenerResponse> implements
        CreateGlobalTaskListenerCommandStep1, CreateGlobalTaskListenerCommandStep1.CreateGlobalTaskListenerCommandStep2,
        CreateGlobalTaskListenerCommandStep1.CreateGlobalTaskListenerCommandStep3,
        CreateGlobalTaskListenerCommandStep1.CreateGlobalTaskListenerCommandStep4 {

    @Override
    public CreateGlobalTaskListenerCommandStep4 retries(Integer retries) {
        return this;
    }

    @Override
    public CreateGlobalTaskListenerCommandStep4 afterNonGlobal(Boolean afterNonGlobal) {
        return this;
    }

    @Override
    public CreateGlobalTaskListenerCommandStep4 beforeNonGlobal() {
        return this;
    }

    @Override
    public CreateGlobalTaskListenerCommandStep4 afterNonGlobal() {
        return this;
    }

    @Override
    public CreateGlobalTaskListenerCommandStep4 priority(Integer priority) {
        return this;
    }

    @Override
    public CreateGlobalTaskListenerCommandStep2 id(String id) {
        return this;
    }

    @Override
    public CreateGlobalTaskListenerCommandStep3 type(String type) {
        return this;
    }

    @Override
    public CreateGlobalTaskListenerCommandStep4 eventTypes(List<GlobalTaskListenerEventType> eventTypes) {
        return this;
    }

    @Override
    public CreateGlobalTaskListenerCommandStep4 eventTypes(GlobalTaskListenerEventType... eventTypes) {
        return this;
    }

    @Override
    public CreateGlobalTaskListenerCommandStep4 eventType(GlobalTaskListenerEventType eventType) {
        return this;
    }
}

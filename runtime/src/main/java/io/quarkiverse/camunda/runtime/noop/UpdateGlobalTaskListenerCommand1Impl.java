package io.quarkiverse.camunda.runtime.noop;

import java.util.List;

import io.camunda.client.api.command.UpdateGlobalTaskListenerCommandStep1;
import io.camunda.client.api.response.GlobalTaskListenerResponse;
import io.camunda.client.api.search.enums.GlobalTaskListenerEventType;

public class UpdateGlobalTaskListenerCommand1Impl extends AbstractStep<GlobalTaskListenerResponse> implements
        UpdateGlobalTaskListenerCommandStep1, UpdateGlobalTaskListenerCommandStep1.UpdateGlobalTaskListenerCommandStep2,
        UpdateGlobalTaskListenerCommandStep1.UpdateGlobalTaskListenerCommandStep3 {

    @Override
    public UpdateGlobalTaskListenerCommandStep3 retries(Integer retries) {
        return this;
    }

    @Override
    public UpdateGlobalTaskListenerCommandStep3 afterNonGlobal(Boolean afterNonGlobal) {
        return this;
    }

    @Override
    public UpdateGlobalTaskListenerCommandStep3 beforeNonGlobal() {
        return this;
    }

    @Override
    public UpdateGlobalTaskListenerCommandStep3 afterNonGlobal() {
        return this;
    }

    @Override
    public UpdateGlobalTaskListenerCommandStep3 priority(Integer priority) {
        return this;
    }

    @Override
    public UpdateGlobalTaskListenerCommandStep2 type(String type) {
        return this;
    }

    @Override
    public UpdateGlobalTaskListenerCommandStep3 eventTypes(List<GlobalTaskListenerEventType> eventTypes) {
        return this;
    }

    @Override
    public UpdateGlobalTaskListenerCommandStep3 eventTypes(GlobalTaskListenerEventType... eventTypes) {
        return this;
    }

    @Override
    public UpdateGlobalTaskListenerCommandStep3 eventType(GlobalTaskListenerEventType eventType) {
        return this;
    }
}

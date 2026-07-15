package io.quarkiverse.camunda.runtime.noop;

import java.io.InputStream;
import java.util.Map;

import io.camunda.client.api.command.ActivateAdHocSubProcessActivitiesCommandStep1;
import io.camunda.client.api.response.ActivateAdHocSubProcessActivitiesResponse;

public class ActivateAdHocSubProcessActivitiesCommand1Impl extends AbstractStep<ActivateAdHocSubProcessActivitiesResponse>
        implements ActivateAdHocSubProcessActivitiesCommandStep1,
        ActivateAdHocSubProcessActivitiesCommandStep1.ActivateAdHocSubProcessActivitiesCommandStep2 {

    @Override
    public ActivateAdHocSubProcessActivitiesCommandStep2 activateElement(String elementId) {
        return this;
    }

    @Override
    public ActivateAdHocSubProcessActivitiesCommandStep2 activateElement(String elementId, Map<String, Object> variables) {
        return this;
    }

    @Override
    public ActivateAdHocSubProcessActivitiesCommandStep2 cancelRemainingInstances(boolean cancelRemainingInstances) {
        return this;
    }

    @Override
    public ActivateAdHocSubProcessActivitiesCommandStep2 variables(String variables) {
        return this;
    }

    @Override
    public ActivateAdHocSubProcessActivitiesCommandStep2 variables(Object variables) {
        return this;
    }

    @Override
    public ActivateAdHocSubProcessActivitiesCommandStep2 variables(InputStream variables) {
        return this;
    }

    @Override
    public ActivateAdHocSubProcessActivitiesCommandStep2 variables(Map<String, Object> variables) {
        return this;
    }

    @Override
    public ActivateAdHocSubProcessActivitiesCommandStep2 variable(String key, Object value) {
        return this;
    }
}

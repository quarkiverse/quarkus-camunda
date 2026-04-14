package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.AssignClientToGroupCommandStep1;
import io.camunda.client.api.response.AssignClientToGroupResponse;

public class AssignClientToGroupCommand1Impl extends AbstractStep<AssignClientToGroupResponse>
        implements AssignClientToGroupCommandStep1, AssignClientToGroupCommandStep1.AssignClientToGroupCommandStep2,
        AssignClientToGroupCommandStep1.AssignClientToGroupCommandStep3 {

    @Override
    public AssignClientToGroupCommandStep2 clientId(String clientId) {
        return this;
    }

    @Override
    public AssignClientToGroupCommandStep3 groupId(String groupId) {
        return this;
    }
}

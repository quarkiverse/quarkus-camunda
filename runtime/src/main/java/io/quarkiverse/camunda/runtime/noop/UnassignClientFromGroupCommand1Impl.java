package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.UnassignClientFromGroupCommandStep1;
import io.camunda.client.api.response.UnassignClientFromGroupResponse;

public class UnassignClientFromGroupCommand1Impl extends AbstractStep<UnassignClientFromGroupResponse>
        implements UnassignClientFromGroupCommandStep1, UnassignClientFromGroupCommandStep1.UnassignClientFromGroupCommandStep2,
        UnassignClientFromGroupCommandStep1.UnassignClientFromGroupCommandStep3 {

    @Override
    public UnassignClientFromGroupCommandStep2 clientId(String clientId) {
        return this;
    }

    @Override
    public UnassignClientFromGroupCommandStep3 groupId(String groupId) {
        return this;
    }
}

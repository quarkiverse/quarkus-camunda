package io.quarkiverse.camunda.runtime.noop;

import java.time.Duration;

import io.camunda.client.api.command.JobChangeset;
import io.camunda.client.api.command.UpdateJobCommandStep1;
import io.camunda.client.api.response.UpdateJobResponse;

public class UpdateJobCommandStep1Impl extends AbstractStep<UpdateJobResponse>
        implements UpdateJobCommandStep1, UpdateJobCommandStep1.UpdateJobCommandStep2 {

    @Override
    public UpdateJobCommandStep2 update(JobChangeset jobChangeset) {
        return this;
    }

    @Override
    public UpdateJobCommandStep2 update(Integer retries, Long timeout) {
        return this;
    }

    @Override
    public UpdateJobCommandStep2 updateRetries(int retries) {
        return this;
    }

    @Override
    public UpdateJobCommandStep2 updateTimeout(long timeout) {
        return this;
    }

    @Override
    public UpdateJobCommandStep2 updateTimeout(Duration timeout) {
        return this;
    }
}

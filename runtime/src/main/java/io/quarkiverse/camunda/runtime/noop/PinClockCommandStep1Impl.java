package io.quarkiverse.camunda.runtime.noop;

import java.time.Instant;

import io.camunda.client.api.command.PinClockCommandStep1;
import io.camunda.client.api.response.PinClockResponse;

public class PinClockCommandStep1Impl extends AbstractStep<PinClockResponse> implements PinClockCommandStep1 {

    @Override
    public PinClockCommandStep1 time(long timestamp) {
        return this;
    }

    @Override
    public PinClockCommandStep1 time(Instant instant) {
        return this;
    }
}

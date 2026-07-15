package io.quarkiverse.camunda.runtime.noop;

import java.time.Duration;

import io.camunda.client.api.CamundaFuture;
import io.camunda.client.api.command.FinalCommandStep;

public class FinalCommandStepImpl<T> implements FinalCommandStep<T> {

    private final T data;

    FinalCommandStepImpl(T data) {
        this.data = data;
    }

    @Override
    public FinalCommandStep<T> requestTimeout(Duration requestTimeout) {
        return this;
    }

    @Override
    public CamundaFuture<T> send() {
        return new FutureImpl<>(data);
    }
}

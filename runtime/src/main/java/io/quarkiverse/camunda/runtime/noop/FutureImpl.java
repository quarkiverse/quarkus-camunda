package io.quarkiverse.camunda.runtime.noop;

import java.util.concurrent.TimeUnit;

import io.camunda.zeebe.client.impl.http.HttpZeebeFuture;

public class FutureImpl<T> extends HttpZeebeFuture<T> {

    private final T response;

    public FutureImpl(T response) {
        this.response = response;
    }

    @Override
    public T join() {
        return response;
    }

    @Override
    public T join(long timeout, TimeUnit unit) {
        return response;
    }

}

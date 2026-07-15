package io.quarkiverse.camunda.runtime.noop;

import java.util.concurrent.TimeUnit;

import io.camunda.client.impl.http.HttpCamundaFuture;

public class FutureImpl<T> extends HttpCamundaFuture<T> {

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

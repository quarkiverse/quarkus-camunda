package io.quarkiverse.camunda.runtime.tracing;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;

/**
 * Shared {@link ForwardingClient.AttributeCallback} implementation, used by both the gRPC
 * ({@link OpenTelemetryClientInterceptor}) and REST ({@link OpenTelemetryHttpChainHandler}) client
 * tracing so command attributes are recorded identically regardless of transport.
 */
final class SpanAttributeCallback implements ForwardingClient.AttributeCallback {

    private final Span span;

    SpanAttributeCallback(Span span) {
        this.span = span;
    }

    @Override
    public ForwardingClient.AttributeCallback setError() {
        this.span.setStatus(StatusCode.ERROR);
        return this;
    }

    @Override
    public ForwardingClient.AttributeCallback setAttribute(String key, String value) {
        this.span.setAttribute(key, value);
        return this;
    }

    @Override
    public ForwardingClient.AttributeCallback setAttribute(String key, int value) {
        this.span.setAttribute(key, value);
        return this;
    }

    @Override
    public ForwardingClient.AttributeCallback setAttribute(String key, long value) {
        this.span.setAttribute(key, value);
        return this;
    }

    @Override
    public ForwardingClient.AttributeCallback setAttribute(String key, boolean value) {
        this.span.setAttribute(key, value);
        return this;
    }
}

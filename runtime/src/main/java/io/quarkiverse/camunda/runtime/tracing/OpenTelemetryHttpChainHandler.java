package io.quarkiverse.camunda.runtime.tracing;

import static io.quarkiverse.camunda.runtime.tracing.Tracing.CLIENT_EXCEPTION;
import static io.quarkus.opentelemetry.runtime.config.build.OTelBuildConfig.INSTRUMENTATION_NAME;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;

import org.apache.hc.client5.http.async.AsyncExecCallback;
import org.apache.hc.client5.http.async.AsyncExecChain;
import org.apache.hc.client5.http.async.AsyncExecChainHandler;
import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.nio.AsyncDataConsumer;
import org.apache.hc.core5.http.nio.AsyncEntityProducer;
import org.apache.hc.core5.http.nio.DataStreamChannel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.StatusCode;

/**
 * The REST/HTTP counterpart to {@link OpenTelemetryClientInterceptor}. Camunda 8.9 clients default to
 * {@code preferRestOverGrpc=true}, so most commands (e.g. completing a job) never go through the gRPC
 * {@code ClientInterceptor} at all - this handler intercepts the Apache HttpClient async exec chain
 * instead, so the same spans/attributes are produced no matter which transport is used.
 */
@ApplicationScoped
public class OpenTelemetryHttpChainHandler implements AsyncExecChainHandler {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final OpenTelemetry openTelemetry;

    public OpenTelemetryHttpChainHandler(OpenTelemetry openTelemetry) {
        this.openTelemetry = openTelemetry;
    }

    @Override
    public void execute(HttpRequest request, AsyncEntityProducer entityProducer, AsyncExecChain.Scope scope,
            AsyncExecChain chain, AsyncExecCallback asyncExecCallback) throws HttpException, IOException {

        Span span = Span.current();
        RestCommand.Match match = span == null ? null : RestCommand.match(request);
        if (match == null) {
            chain.proceed(request, entityProducer, scope, asyncExecCallback);
            return;
        }

        Span callSpan = openTelemetry.getTracer(INSTRUMENTATION_NAME)
                .spanBuilder(match.command().spanName())
                .setSpanKind(SpanKind.CLIENT).startSpan();

        AsyncEntityProducer tracedEntityProducer = entityProducer;
        if (match.command().hasJsonBody() && entityProducer != null) {
            tracedEntityProducer = new CapturingEntityProducer(entityProducer,
                    bytes -> applyAttributes(match, bytes, callSpan));
        } else {
            applyAttributes(match, null, callSpan);
        }

        try (io.opentelemetry.context.Scope ignored = callSpan.makeCurrent()) {
            chain.proceed(request, tracedEntityProducer, scope, wrap(asyncExecCallback, callSpan));
        } catch (Throwable t) {
            callSpan.setStatus(StatusCode.ERROR).setAttribute(CLIENT_EXCEPTION, t.getMessage());
            callSpan.end();
            throw t;
        }
    }

    private static void applyAttributes(RestCommand.Match match, byte[] body, Span callSpan) {
        JsonNode node = MissingNode.getInstance();
        if (body != null && body.length > 0) {
            try {
                node = MAPPER.readTree(body);
            } catch (IOException e) {
                // not a JSON body (or empty) - fall back to path-derived attributes only
            }
        }
        match.applyAttributes(node, new SpanAttributeCallback(callSpan));
    }

    private static AsyncExecCallback wrap(AsyncExecCallback delegate, Span callSpan) {
        return new AsyncExecCallback() {

            @Override
            public AsyncDataConsumer handleResponse(HttpResponse response, EntityDetails entityDetails)
                    throws HttpException, IOException {
                return delegate.handleResponse(response, entityDetails);
            }

            @Override
            public void handleInformationResponse(HttpResponse response) throws HttpException, IOException {
                delegate.handleInformationResponse(response);
            }

            @Override
            public void completed() {
                callSpan.end();
                delegate.completed();
            }

            @Override
            public void failed(Exception cause) {
                callSpan.setStatus(StatusCode.ERROR).setAttribute(CLIENT_EXCEPTION, cause.getMessage());
                callSpan.end();
                delegate.failed(cause);
            }
        };
    }

    /**
     * Tees the bytes of the request body as they're actually written to the channel, then hands the
     * fully captured body to {@code onComplete} once the stream ends - without altering what's sent.
     */
    private static final class CapturingEntityProducer implements AsyncEntityProducer {

        private final AsyncEntityProducer delegate;
        private final java.util.function.Consumer<byte[]> onComplete;
        private final ByteArrayOutputStream captured = new ByteArrayOutputStream();
        private volatile boolean completed;

        CapturingEntityProducer(AsyncEntityProducer delegate, java.util.function.Consumer<byte[]> onComplete) {
            this.delegate = delegate;
            this.onComplete = onComplete;
        }

        @Override
        public boolean isRepeatable() {
            return delegate.isRepeatable();
        }

        @Override
        public void failed(Exception cause) {
            delegate.failed(cause);
        }

        @Override
        public long getContentLength() {
            return delegate.getContentLength();
        }

        @Override
        public String getContentType() {
            return delegate.getContentType();
        }

        @Override
        public String getContentEncoding() {
            return delegate.getContentEncoding();
        }

        @Override
        public boolean isChunked() {
            return delegate.isChunked();
        }

        @Override
        public Set<String> getTrailerNames() {
            return delegate.getTrailerNames();
        }

        @Override
        public int available() {
            return delegate.available();
        }

        @Override
        public void produce(DataStreamChannel channel) throws IOException {
            delegate.produce(new DataStreamChannel() {

                @Override
                public void requestOutput() {
                    channel.requestOutput();
                }

                @Override
                public int write(ByteBuffer src) throws IOException {
                    int position = src.position();
                    int written = channel.write(src);
                    if (written > 0) {
                        ByteBuffer writtenSlice = src.duplicate();
                        writtenSlice.position(position);
                        writtenSlice.limit(position + written);
                        byte[] bytes = new byte[written];
                        writtenSlice.get(bytes);
                        captured.write(bytes);
                    }
                    return written;
                }

                @Override
                public void endStream(List<? extends Header> trailers) throws IOException {
                    complete();
                    channel.endStream(trailers);
                }

                @Override
                public void endStream() throws IOException {
                    complete();
                    channel.endStream();
                }
            });
        }

        private void complete() {
            if (!completed) {
                completed = true;
                onComplete.accept(captured.toByteArray());
            }
        }

        @Override
        public void releaseResources() {
            delegate.releaseResources();
        }
    }
}

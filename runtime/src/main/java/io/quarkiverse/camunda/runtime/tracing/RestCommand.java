package io.quarkiverse.camunda.runtime.tracing;

import static io.quarkiverse.camunda.runtime.tracing.Tracing.FAIL_MESSAGE;
import static io.quarkiverse.camunda.runtime.tracing.Tracing.INCIDENT_KEY;
import static io.quarkiverse.camunda.runtime.tracing.Tracing.JOB_KEY;
import static io.quarkiverse.camunda.runtime.tracing.Tracing.JOB_RETRIES;
import static io.quarkiverse.camunda.runtime.tracing.Tracing.JOB_VARIABLES;
import static io.quarkiverse.camunda.runtime.tracing.Tracing.MESSAGE_CORRELATION_KEY;
import static io.quarkiverse.camunda.runtime.tracing.Tracing.MESSAGE_ID;
import static io.quarkiverse.camunda.runtime.tracing.Tracing.MESSAGE_NAME;
import static io.quarkiverse.camunda.runtime.tracing.Tracing.MESSAGE_TIME_TO_LIVE;
import static io.quarkiverse.camunda.runtime.tracing.Tracing.MESSAGE_VARIABLES;
import static io.quarkiverse.camunda.runtime.tracing.Tracing.PROCESS_ELEMENT_INSTANCE_KEY;
import static io.quarkiverse.camunda.runtime.tracing.Tracing.PROCESS_INSTANCE_KEY;
import static io.quarkiverse.camunda.runtime.tracing.Tracing.PROCESS_VARIABLES;
import static io.quarkiverse.camunda.runtime.tracing.Tracing.PROCESS_VARIABLES_SCOPE;
import static io.quarkiverse.camunda.runtime.tracing.Tracing.REQUEST_TIMEOUT;
import static io.quarkiverse.camunda.runtime.tracing.Tracing.THROW_ERROR_CODE;
import static io.quarkiverse.camunda.runtime.tracing.Tracing.THROW_ERROR_MESSAGE;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hc.core5.http.HttpRequest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.MissingNode;

import io.quarkiverse.camunda.runtime.tracing.ForwardingClient.AttributeCallback;

/**
 * Maps a REST API call made by the Camunda client (method + path) to the same span name and
 * attributes the gRPC transport produces via {@link ForwardingClient}, so tracing stays consistent
 * regardless of whether {@code preferRestOverGrpc} is enabled.
 */
final class RestCommand {

    interface AttributeExtractor {
        void apply(Matcher path, JsonNode body, AttributeCallback span);
    }

    private static final List<RestCommand> COMMANDS = List.of(
            new RestCommand("POST", "/jobs/(\\d+)/completion", "CompleteJobRequest", true,
                    (path, body, span) -> {
                        span.setAttribute(JOB_KEY, Long.parseLong(path.group(1)));
                        ifPresent(body, "variables", n -> span.setAttribute(JOB_VARIABLES, n.toString()));
                    }),
            new RestCommand("POST", "/process-instances", "CreateProcessInstanceRequest", true,
                    (path, body, span) -> ifPresent(body, "requestTimeout",
                            n -> span.setAttribute(REQUEST_TIMEOUT, n.asLong()))),
            new RestCommand("POST", "/jobs/(\\d+)/failure", "FailJobRequest", true,
                    (path, body, span) -> {
                        span.setError();
                        span.setAttribute(JOB_KEY, Long.parseLong(path.group(1)));
                        ifPresent(body, "retries", n -> span.setAttribute(JOB_RETRIES, n.asInt()));
                        ifPresent(body, "errorMessage", n -> span.setAttribute(FAIL_MESSAGE, n.asText()));
                    }),
            new RestCommand("POST", "/jobs/(\\d+)/error", "ThrowErrorRequest", true,
                    (path, body, span) -> {
                        span.setError();
                        span.setAttribute(JOB_KEY, Long.parseLong(path.group(1)));
                        ifPresent(body, "errorMessage", n -> span.setAttribute(THROW_ERROR_MESSAGE, n.asText()));
                        ifPresent(body, "errorCode", n -> span.setAttribute(THROW_ERROR_CODE, n.asText()));
                    }),
            new RestCommand("POST", "/messages/publication", "PublishMessageRequest", true,
                    (path, body, span) -> {
                        ifPresent(body, "correlationKey", n -> span.setAttribute(MESSAGE_CORRELATION_KEY, n.asText()));
                        ifPresent(body, "messageId", n -> span.setAttribute(MESSAGE_ID, n.asText()));
                        ifPresent(body, "name", n -> span.setAttribute(MESSAGE_NAME, n.asText()));
                        ifPresent(body, "timeToLive", n -> span.setAttribute(MESSAGE_TIME_TO_LIVE, n.asLong()));
                        ifPresent(body, "variables", n -> span.setAttribute(MESSAGE_VARIABLES, n.toString()));
                    }),
            new RestCommand("POST", "/process-instances/(\\d+)/cancellation", "CancelProcessInstanceRequest", false,
                    (path, body, span) -> span.setAttribute(PROCESS_INSTANCE_KEY, Long.parseLong(path.group(1)))),
            new RestCommand("POST", "/incidents/(\\d+)/resolution", "ResolveIncidentRequest", false,
                    (path, body, span) -> span.setAttribute(INCIDENT_KEY, Long.parseLong(path.group(1)))),
            new RestCommand("PUT", "/element-instances/(\\d+)/variables", "SetVariablesRequest", true,
                    (path, body, span) -> {
                        span.setAttribute(PROCESS_ELEMENT_INSTANCE_KEY, Long.parseLong(path.group(1)));
                        ifPresent(body, "variables", n -> span.setAttribute(PROCESS_VARIABLES, n.toString()));
                        span.setAttribute(PROCESS_VARIABLES_SCOPE, body.path("local").asBoolean(false));
                    }),
            new RestCommand("PATCH", "/jobs/(\\d+)", "UpdateJobRetriesRequest", true,
                    (path, body, span) -> {
                        span.setAttribute(JOB_KEY, Long.parseLong(path.group(1)));
                        ifPresent(body.path("changeset"), "retries", n -> span.setAttribute(JOB_RETRIES, n.asInt()));
                    }),
            new RestCommand("POST", "/deployments", "DeployResourceRequest", false, (path, body, span) -> {
            }));

    private final String method;
    private final Pattern path;
    private final String spanName;
    private final boolean hasJsonBody;
    private final AttributeExtractor attributes;

    private RestCommand(String method, String pathRegex, String spanName, boolean hasJsonBody,
            AttributeExtractor attributes) {
        this.method = method;
        // the REST API is versioned (e.g. "/v2/jobs/{key}/completion"), so skip any leading segments
        this.path = Pattern.compile("^.*?" + pathRegex + "/?$");
        this.spanName = spanName;
        this.hasJsonBody = hasJsonBody;
        this.attributes = attributes;
    }

    String spanName() {
        return spanName;
    }

    boolean hasJsonBody() {
        return hasJsonBody;
    }

    static Match match(HttpRequest request) {
        String requestPath = request.getPath();
        int query = requestPath.indexOf('?');
        if (query >= 0) {
            requestPath = requestPath.substring(0, query);
        }
        for (RestCommand command : COMMANDS) {
            if (!command.method.equalsIgnoreCase(request.getMethod())) {
                continue;
            }
            Matcher matcher = command.path.matcher(requestPath);
            if (matcher.matches()) {
                return new Match(command, matcher);
            }
        }
        return null;
    }

    private static void ifPresent(JsonNode body, String field, java.util.function.Consumer<JsonNode> consumer) {
        JsonNode node = body.get(field);
        if (node != null && !node.isNull() && !(node instanceof MissingNode)) {
            consumer.accept(node);
        }
    }

    static final class Match {
        private final RestCommand command;
        private final Matcher path;

        private Match(RestCommand command, Matcher path) {
            this.command = command;
            this.path = path;
        }

        RestCommand command() {
            return command;
        }

        void applyAttributes(JsonNode body, AttributeCallback span) {
            command.attributes.apply(path, body, span);
        }
    }
}

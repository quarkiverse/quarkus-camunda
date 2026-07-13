package io.quarkiverse.camunda.it.opentelemetry;

import static io.quarkiverse.camunda.it.opentelemetry.TestResources.JAEGER_HOST;
import static io.quarkiverse.camunda.it.opentelemetry.TestResources.JAEGER_PORT;
import static io.restassured.RestAssured.given;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.ws.rs.core.Response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.camunda.client.CamundaClient;
import io.camunda.client.api.response.ProcessInstanceEvent;
import io.camunda.process.test.api.CamundaAssert;
import io.quarkiverse.camunda.test.CamundaTestResource;
import io.quarkiverse.camunda.test.InjectCamundaClient;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

@QuarkusTest
@DisplayName("Open-telemetry test")
@QuarkusTestResource(CamundaTestResource.class)
@QuarkusTestResource(TestResources.class)
public class OpentelemetryTest {

    //Configure the containers for the test
    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        //        RestAssured.filters(new ResponseLoggingFilter());
    }

    private final static String BPM_PROCESS_ID = "test";

    @InjectCamundaClient
    CamundaClient client;

    @Test
    @DisplayName("Test open telemetry")
    public void testOpenTelemetry() {

        Parameter param = new Parameter();
        param.message = "message-example";
        param.name = "name-input";

        ProcessInstanceEvent event = client
                .newCreateInstanceCommand()
                .bpmnProcessId(BPM_PROCESS_ID)
                .latestVersion()
                .variables(param)
                .send().join();

        Assertions.assertEquals(BPM_PROCESS_ID, event.getBpmnProcessId());
        CamundaAssert.assertThat(event).isCompleted();

        // the job-worker span and the "CompleteJobRequest" client span it triggers are exported
        // asynchronously and independently, so wait until both have arrived instead of just any data
        Map<String, JaegerSpan> spans = new HashMap<>();
        await().atMost(7, SECONDS).until(() -> {
            spans.clear();
            spans.putAll(collectSpans(jaegerTrace()));
            return spans.containsKey("openTelemetryTestMethod") && spans.containsKey("CompleteJobRequest");
        });

        JaegerSpan testMethod = spans.get("openTelemetryTestMethod");
        Assertions.assertNotNull(testMethod);
        Map<String, JaegerTag> testTags = testMethod.tags.stream()
                .collect(Collectors.toMap(x -> x.key, x -> x));
        //        assertTag(testTags, "otel.library.name", "io.quarkus.opentelemetry");
        assertTag(testTags, "bpmn-component", "job-worker");
        assertTag(testTags, "bpmn-process-id", "test");
        assertTag(testTags, "bpmn-process-element-id", "Activity_0apsury");
        assertTag(testTags, "bpmn-process-def-ver", "1");
        assertTag(testTags, "bpmn-retries", "3");
        assertTag(testTags, "bpmn-class", "io.quarkiverse.camunda.it.opentelemetry.OpentelemetryTestJobWorker");
        assertTag(testTags, "bpmn-job-variables", "{\"name\":\"name-input\",\"message\":\"message-example\"}");
        assertTag(testTags, "bpmn-job-type", "test");

        JaegerSpan completeSpan = spans.get("CompleteJobRequest");
        Assertions.assertNotNull(completeSpan);
        Map<String, JaegerTag> completeTags = completeSpan.tags.stream()
                .collect(Collectors.toMap(x -> x.key, x -> x));
        //        assertTag(completeTags, "otel.library.name", "io.quarkus.opentelemetry");
        assertTag(completeTags, "bpmn-job-variables", "{\"name\":\"name-input\",\"message\":\"Ok\"}");

    }

    private void assertTag(Map<String, JaegerTag> tags, String name, Object value) {
        Assertions.assertNotNull(tags.get(name));
        Assertions.assertEquals(value, tags.get(name).value);
    }

    private Map<String, JaegerSpan> collectSpans(JsonPath response) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JaegerResponse jr;
        try {
            jr = objectMapper.readValue(response.prettify(), JaegerResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Map<String, JaegerSpan> spans = new HashMap<>();
        jr.data.forEach(x -> x.spans.forEach(y -> spans.put(y.operationName, y)));
        return spans;
    }

    private JsonPath jaegerTrace() {
        return given().baseUri(JAEGER_HOST).port(JAEGER_PORT)
                .when()
                .get("/api/traces?service=quarkus-camunda-integration-tests-opentelemetry&limit=40&lookback=1h")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract()
                .jsonPath();
    }

    public static class JaegerResponse {
        public List<JaegerTrace> data;
    }

    public static class JaegerTrace {
        public List<JaegerSpan> spans;
    }

    public static class JaegerSpan {
        public String operationName;

        public List<JaegerTag> tags;
    }

    public static class JaegerTag {

        public String key;
        public String value;
    }
}

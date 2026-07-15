package io.quarkiverse.camunda.it.bpmn.errors;

import static io.camunda.process.test.api.CamundaAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.camunda.client.CamundaClient;
import io.camunda.client.api.response.ProcessInstanceEvent;
import io.camunda.client.api.search.enums.IncidentErrorType;
import io.camunda.client.api.search.response.Incident;
import io.camunda.process.test.api.assertions.ProcessInstanceAssert;
import io.quarkiverse.camunda.it.bpmn.AbstractTest;
import io.quarkiverse.camunda.test.InjectCamundaClient;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@DisplayName("Errors Test")
public class ErrorsTest extends AbstractTest {

    @InjectCamundaClient
    CamundaClient client;

    private final static String FAIL_PROCESS_ID = "fail-process";
    private final static String THROW_ERROR_PROCESS_ID = "throw-zeebe-error-process";
    private final static String THROW_ERROR_EVENT_PROCESS_ID = "throw-zeebe-error-event-process";
    private final static String THROW_RUNTIME_EXCEPTION_PROCESS_ID = "throw-runtime-exception-process";

    @Test
    @DisplayName("Start throw zeebe error process")
    public void throwZeebeBpmnErrorTest() {

        ProcessInstanceEvent event = client
                .newCreateInstanceCommand()
                .bpmnProcessId(THROW_ERROR_PROCESS_ID)
                .latestVersion()
                .send().join();

        Assertions.assertEquals(THROW_ERROR_PROCESS_ID, event.getBpmnProcessId());

        assertThat(event).hasActiveIncidents();
        Incident incident = latestIncident(event.getProcessInstanceKey());
        Assertions.assertEquals(IncidentErrorType.UNHANDLED_ERROR_EVENT, incident.getErrorType());
        Assertions.assertEquals(
                "Expected to throw an error event with the code 'error-code' with message 'error-message', but it was not caught. No error events are available in the scope.",
                incident.getErrorMessage());
    }

    @Test
    @DisplayName("Start throw zeebe error event process")
    public void throwZeebeBpmnErrorEventTest() {

        ProcessInstanceEvent event = client
                .newCreateInstanceCommand()
                .bpmnProcessId(THROW_ERROR_EVENT_PROCESS_ID)
                .latestVersion()
                .send().join();

        Assertions.assertEquals(THROW_ERROR_EVENT_PROCESS_ID, event.getBpmnProcessId());

        ProcessInstanceAssert a = assertThat(event).isCompleted();
    }

    @Test
    @DisplayName("Start throw runtime exception process")
    public void throwRuntimeExceptionTest() {

        ProcessInstanceEvent event = client
                .newCreateInstanceCommand()
                .bpmnProcessId(THROW_RUNTIME_EXCEPTION_PROCESS_ID)
                .latestVersion()
                .send().join();

        Assertions.assertEquals(THROW_RUNTIME_EXCEPTION_PROCESS_ID, event.getBpmnProcessId());

        assertThat(event).hasActiveIncidents();
        Incident incident = latestIncident(event.getProcessInstanceKey());
        Assertions.assertEquals(IncidentErrorType.JOB_NO_RETRIES, incident.getErrorType());
        Assertions.assertTrue(incident.getErrorMessage().startsWith("java.lang.RuntimeException: error-code"));
    }

    @Test
    @DisplayName("Start fail process")
    public void failTest() {

        ProcessInstanceEvent event = client
                .newCreateInstanceCommand()
                .bpmnProcessId(FAIL_PROCESS_ID)
                .latestVersion()
                .send().join();

        Assertions.assertEquals(FAIL_PROCESS_ID, event.getBpmnProcessId());

        assertThat(event).hasActiveIncidents();
        Incident incident = latestIncident(event.getProcessInstanceKey());
        Assertions.assertEquals(IncidentErrorType.JOB_NO_RETRIES, incident.getErrorType());
        Assertions.assertTrue(incident.getErrorMessage().startsWith("error message"));
    }

    private Incident latestIncident(long processInstanceKey) {
        List<Incident> incidents = client.newIncidentsByProcessInstanceSearchRequest(processInstanceKey)
                .sort(s -> s.creationTime().desc())
                .send().join().items();
        return incidents.get(0);
    }
}

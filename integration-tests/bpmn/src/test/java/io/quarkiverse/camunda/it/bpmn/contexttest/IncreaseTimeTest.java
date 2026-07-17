package io.quarkiverse.camunda.it.bpmn.contexttest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.camunda.client.CamundaClient;
import io.camunda.client.api.response.ProcessInstanceEvent;
import io.camunda.process.test.api.CamundaAssert;
import io.camunda.process.test.api.CamundaProcessTestContext;
import io.quarkiverse.camunda.it.bpmn.AbstractTest;
import io.quarkiverse.camunda.test.InjectCamundaClient;
import io.quarkiverse.camunda.test.InjectCamundaTestContext;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@DisplayName("Increase time Test")
public class IncreaseTimeTest extends AbstractTest {

    private static final String BPMN_PROCESS_ID = "increase-time-test";
    private static final String MOCKED_JOB_TYPE = "context-test-mock-job";
    private static final String TIMER_ELEMENT_ID = "wait-timer";

    @InjectCamundaClient
    CamundaClient client;
    @InjectCamundaTestContext
    CamundaProcessTestContext camundaProcessTestContext;

    @Test
    @DisplayName("Mock a job worker and fast-forward past a BPMN timer")
    public void mocksJobWorkerAndAdvancesTime() {
        camundaProcessTestContext.mockJobWorker(MOCKED_JOB_TYPE)
                .thenComplete(Map.of("mocked", true));

        Instant timeBeforeIncrease = camundaProcessTestContext.getCurrentTime();

        ProcessInstanceEvent event = client
                .newCreateInstanceCommand()
                .bpmnProcessId(BPMN_PROCESS_ID)
                .latestVersion()
                .send().join();

        CamundaAssert.assertThat(event)
                .hasVariable("mocked", true)
                .hasActiveElements(TIMER_ELEMENT_ID);

        camundaProcessTestContext.increaseTime(Duration.ofHours(1L));

        assertTrue(camundaProcessTestContext.getCurrentTime().isAfter(timeBeforeIncrease));

        CamundaAssert.assertThat(event)
                .isCompleted();
    }
}

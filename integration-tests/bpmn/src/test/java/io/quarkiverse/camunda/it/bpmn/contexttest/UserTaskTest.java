package io.quarkiverse.camunda.it.bpmn.contexttest;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.camunda.client.CamundaClient;
import io.camunda.client.api.response.ProcessInstanceEvent;
import io.camunda.process.test.api.CamundaAssert;
import io.camunda.process.test.api.CamundaProcessTestContext;
import io.camunda.process.test.api.assertions.UserTaskSelectors;
import io.quarkiverse.camunda.it.bpmn.AbstractTest;
import io.quarkiverse.camunda.test.InjectCamundaClient;
import io.quarkiverse.camunda.test.InjectCamundaTestContext;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@DisplayName("User Task Test")
public class UserTaskTest extends AbstractTest {

    private static final String BPMN_PROCESS_ID = "Process_1p92su8";
    private static final String USER_TASK_NAME = "HT_Test";

    @InjectCamundaClient
    CamundaClient client;
    @InjectCamundaTestContext
    CamundaProcessTestContext camundaProcessTestContext;

    @Test
    @DisplayName("Complete a user task through the process test context")
    public void completesUserTaskThroughContext() {
        ProcessInstanceEvent event = client
                .newCreateInstanceCommand()
                .bpmnProcessId(BPMN_PROCESS_ID)
                .latestVersion()
                .send().join();

        CamundaAssert.assertThatUserTask(UserTaskSelectors.byTaskName(USER_TASK_NAME))
                .isCreated();

        camundaProcessTestContext.completeUserTask(
                UserTaskSelectors.byTaskName(USER_TASK_NAME),
                Map.of("outcome", "approved"));

        CamundaAssert.assertThat(event)
                .isCompleted()
                .hasVariable("outcome", "approved");
    }
}
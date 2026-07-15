package io.quarkiverse.camunda.it.bpmn.parameters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.camunda.client.CamundaClient;
import io.camunda.client.api.response.ProcessInstanceEvent;
import io.camunda.process.test.api.CamundaAssert;
import io.quarkiverse.camunda.it.bpmn.AbstractTest;
import io.quarkiverse.camunda.test.InjectCamundaClient;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@DisplayName("Parameters Test")
public class ParametersTest extends AbstractTest {

    private static final String BPM_PROCESS_ID = "ParametersProcessTest";

    @InjectCamundaClient
    CamundaClient client;

    @Test
    @DisplayName("Start process")
    public void sayHelloTest() {

        Parameter p = new Parameter();
        p.info = "info-input";
        p.data = "data-input";

        ProcessInstanceEvent event = client
                .newCreateInstanceCommand()
                .bpmnProcessId(BPM_PROCESS_ID)
                .latestVersion()
                .variables(p)
                .send().join();

        Assertions.assertEquals(BPM_PROCESS_ID, event.getBpmnProcessId());

        CamundaAssert.assertThat(event).isCompleted();

    }

}

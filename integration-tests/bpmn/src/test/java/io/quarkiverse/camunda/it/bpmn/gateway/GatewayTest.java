package io.quarkiverse.camunda.it.bpmn.gateway;

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
@DisplayName("Gateway Test")
public class GatewayTest extends AbstractTest {

    @InjectCamundaClient
    CamundaClient client;

    private final static String BPM_PROCESS_ID = "gateway";

    @Test
    @DisplayName("Start gateway process without parameters")
    public void gatewayWithoutParameterTest() {

        Input p = new Input();
        p.message = "test-message";
        p.read = false;

        ProcessInstanceEvent event = client
                .newCreateInstanceCommand()
                .bpmnProcessId(BPM_PROCESS_ID)
                .latestVersion()
                .variables(p)
                .send().join();

        Assertions.assertEquals(BPM_PROCESS_ID, event.getBpmnProcessId());

        CamundaAssert.assertThat(event)
                .isCompleted()
                .hasVariable("read", false)
                .hasVariable("info", "empty data");
    }

    @Test
    @DisplayName("Start gateway process with parameters")
    public void gatewayWithParameterTest() {

        Input p = new Input();
        p.read = true;

        ProcessInstanceEvent event = client
                .newCreateInstanceCommand()
                .bpmnProcessId(BPM_PROCESS_ID)
                .latestVersion()
                .variables(p)
                .send().join();

        Assertions.assertEquals(BPM_PROCESS_ID, event.getBpmnProcessId());

        CamundaAssert.assertThat(event)
                .isCompleted()
                .hasVariable("read", true)
                .hasVariable("info", "update data")
                .hasVariable("data", "update[read data]");
    }
}

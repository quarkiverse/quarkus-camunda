package io.quarkiverse.camunda.it.docker.sayhello;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.camunda.client.CamundaClient;
import io.camunda.client.api.response.ProcessInstanceEvent;
import io.camunda.process.test.api.CamundaAssert;
import io.quarkiverse.camunda.it.docker.AbstractTest;
import io.quarkiverse.camunda.test.InjectCamundaClient;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@DisplayName("Say Hello Test")
public class SayHelloTest extends AbstractTest {

    @InjectCamundaClient
    CamundaClient client;

    @Test
    @DisplayName("Start process")
    public void sayHelloTest() {

        SayHelloParameter p = new SayHelloParameter();
        p.message = "message-example";
        p.name = "name-input";

        ProcessInstanceEvent event = client
                .newCreateInstanceCommand()
                .bpmnProcessId("hello_process")
                .latestVersion()
                .variables(p)
                .send().join();

        CamundaAssert.assertThat(event).isCompleted()
                .hasVariable("name", "name-input")
                .hasVariable("message", "Hello name-input");
    }

}

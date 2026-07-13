package io.quarkiverse.camunda.it.bpmn;

import io.quarkiverse.camunda.test.CamundaTestResource;
import io.quarkus.test.common.QuarkusTestResource;
import io.restassured.RestAssured;

@QuarkusTestResource(CamundaTestResource.class)
public class AbstractTest {

    //Configure the containers for the test
    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

}

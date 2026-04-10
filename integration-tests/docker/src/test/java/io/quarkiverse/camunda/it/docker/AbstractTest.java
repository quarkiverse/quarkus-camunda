package io.quarkiverse.camunda.it.docker;

import io.quarkiverse.camunda.test.CamundaTestResource;
import io.quarkus.test.common.QuarkusTestResource;
import io.restassured.RestAssured;
import io.restassured.filter.log.ResponseLoggingFilter;

@QuarkusTestResource(CamundaTestResource.class)
public class AbstractTest {

    //Configure the containers for the test
    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(new ResponseLoggingFilter());
    }

}

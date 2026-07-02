package io.quarkiverse.camunda.it.bpmn.sayhello;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.quarkiverse.camunda.it.bpmn.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

@QuarkusTest
@DisplayName("Say Hello Test")
public class SayHelloTest extends AbstractTest {

    @Test
    @DisplayName("Start process")
    public void sayHelloTest() {

        SayHelloParameter p = new SayHelloParameter();
        p.message = "message-test-example";
        p.name = "name-test-input";

        ExtractableResponse<Response> extract = given().contentType(ContentType.JSON)
                .body(p).when()
                .post("/say-hello")
                .then().log().body().extract();

        long processInstanceKey = extract
                .jsonPath().getLong("processInstanceKey");
        String workerVariable = extract
                .jsonPath().getString("variablesAsMap.name");

        Assertions.assertNotNull(workerVariable);

    }

}

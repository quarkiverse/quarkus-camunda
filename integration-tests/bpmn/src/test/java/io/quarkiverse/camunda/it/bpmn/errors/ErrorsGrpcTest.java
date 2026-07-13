package io.quarkiverse.camunda.it.bpmn.errors;

import org.junit.jupiter.api.DisplayName;

import io.quarkiverse.camunda.it.bpmn.UseGrpcProfile;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;

@QuarkusTest
@DisplayName("Errors GRPC Test")
@TestProfile(UseGrpcProfile.class)
public class ErrorsGrpcTest extends ErrorsTest {

}

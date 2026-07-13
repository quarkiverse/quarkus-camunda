package io.quarkiverse.camunda.it.bpmn.parameters;

import org.junit.jupiter.api.DisplayName;

import io.quarkiverse.camunda.it.bpmn.UseGrpcProfile;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;

@QuarkusTest
@DisplayName("Parameter GRPC Test")
@TestProfile(UseGrpcProfile.class)
public class ParametersGrpcTest extends ParametersTest {
}

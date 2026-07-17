package io.quarkiverse.camunda.it.bpmn.contexttest;

import org.junit.jupiter.api.DisplayName;

import io.quarkiverse.camunda.it.bpmn.UseGrpcProfile;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;

@QuarkusTest
@DisplayName("Increase Time GRPC Test")
@TestProfile(UseGrpcProfile.class)
public class IncreaseTimeGrpcTest extends IncreaseTimeTest {
}

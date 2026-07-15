package io.quarkiverse.camunda.it.bpmn.sayhello;

import org.junit.jupiter.api.DisplayName;

import io.quarkiverse.camunda.it.bpmn.UseGrpcProfile;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;

@QuarkusTest
@DisplayName("Say Hello GRPC Test")
@TestProfile(UseGrpcProfile.class)
public class SayHelloGrpcTest extends SayHelloTest {
}

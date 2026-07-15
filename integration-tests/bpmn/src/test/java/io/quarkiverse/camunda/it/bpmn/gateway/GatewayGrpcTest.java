package io.quarkiverse.camunda.it.bpmn.gateway;

import org.junit.jupiter.api.DisplayName;

import io.quarkiverse.camunda.it.bpmn.UseGrpcProfile;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;

@QuarkusTest
@DisplayName("Gateway GRPC Test")
@TestProfile(UseGrpcProfile.class)
public class GatewayGrpcTest extends GatewayTest {
}

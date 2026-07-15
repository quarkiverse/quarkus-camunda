package io.quarkiverse.camunda.it.opentelemetry;

import org.junit.jupiter.api.DisplayName;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;

@QuarkusTest
@DisplayName("OpenTelemetry GRPC Test")
@TestProfile(UseGrpcProfile.class)
public class OpentelemetryGrpcTest extends OpentelemetryTest {
}

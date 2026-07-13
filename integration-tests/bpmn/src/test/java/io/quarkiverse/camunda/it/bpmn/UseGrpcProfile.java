package io.quarkiverse.camunda.it.bpmn;

import java.util.Collections;
import java.util.Map;

import io.quarkus.test.junit.QuarkusTestProfile;

public class UseGrpcProfile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
        return Collections.singletonMap("quarkus.camunda.client.broker.use-grpc", "true");
    }
}

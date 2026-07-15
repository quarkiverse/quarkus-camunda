package io.quarkiverse.camunda;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.camunda.client.api.response.Topology;
import io.quarkiverse.camunda.test.TestService;
import io.quarkus.test.QuarkusUnitTest;

public class ClientConnectionTest {

    @RegisterExtension
    static final QuarkusUnitTest unitTest = new QuarkusUnitTest()
            .withApplicationRoot((jar) -> jar
                    .addAsResource("no-resources.properties", "application.properties")
                    .addClasses(TestService.class));

    @Inject
    TestService config;

    @Test
    @DisplayName("Test default connection")
    public void testLiquibaseNotAvailableWithoutDataSource() {
        Assertions.assertNotNull(config);
        Topology topology = config.topology();
        Assertions.assertNotNull(topology);
    }

}

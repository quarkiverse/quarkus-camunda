package io.quarkiverse.camunda.test;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;

import io.camunda.client.CamundaClient;
import io.camunda.client.impl.CamundaObjectMapper;
import io.camunda.process.test.api.CamundaProcessTestContext;
import io.camunda.process.test.impl.assertions.util.AwaitilityBehavior;
import io.camunda.process.test.impl.client.CamundaManagementClient;
import io.camunda.process.test.impl.extension.CamundaProcessTestContextImpl;
import io.camunda.process.test.impl.extension.ConditionalBehaviorEngine;
import io.camunda.zeebe.client.impl.ZeebeObjectMapper;
import io.quarkus.test.common.DevServicesContext;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class CamundaTestResource implements QuarkusTestResourceLifecycleManager, DevServicesContext.ContextAware {

    private static final Logger log = Logger.getLogger(CamundaTestResource.class);
    private CamundaClient camundaClient;
    private final List<AutoCloseable> createdClients = new ArrayList<>();
    private CamundaProcessTestContext camundaProcessTestContext;

    @Override
    public Map<String, String> start() {
        return null;
    }

    @Override
    public void stop() {
        CamundaAssertInitializer.reset();
        closeCreatedClients();
    }

    @Override
    public void inject(TestInjector testInjector) {
        testInjector.injectIntoFields(camundaClient,
                new TestInjector.AnnotatedAndMatchesType(InjectCamundaClient.class, CamundaClient.class));
        testInjector.injectIntoFields(camundaProcessTestContext,
                new TestInjector.AnnotatedAndMatchesType(InjectCamundaTestContext.class, CamundaProcessTestContext.class));
    }

    @Override
    public void setIntegrationTestContext(DevServicesContext context) {
        String grpcAddress = context.devServicesProperties().get("quarkiverse.camunda.devservices.test.gateway-address");
        String restAddress = context.devServicesProperties().get("quarkiverse.camunda.devservices.test.rest-address");
        String monitoringAddress = context.devServicesProperties()
                .get("quarkiverse.camunda.devservices.test.monitoring-address");
        if (grpcAddress != null && restAddress != null) {
            camundaProcessTestContext = createContext(URI.create(restAddress), URI.create(grpcAddress),
                    URI.create(monitoringAddress));
            camundaClient = camundaProcessTestContext.createClient();
            CamundaAssertInitializer.initialize(camundaClient);
        }
    }

    private void closeCreatedClients() {
        for (final AutoCloseable client : createdClients) {
            try {
                client.close();
            } catch (final Exception e) {
                log.debug("Failed to close client, continue.", e);
            }
        }
        createdClients.clear();
    }

    private CamundaProcessTestContext createContext(URI restAddress, URI grpcAddress,
            URI monitoringAddress) {
        return new CamundaProcessTestContextImpl(
                new QuarkusCamundaProcessTestRuntime(restAddress, grpcAddress, monitoringAddress),
                createdClients::add,
                CamundaManagementClient.createClient(monitoringAddress),
                AwaitilityBehavior::new,
                new CamundaObjectMapper(),
                new ZeebeObjectMapper(),
                new ConditionalBehaviorEngine());
    }
}

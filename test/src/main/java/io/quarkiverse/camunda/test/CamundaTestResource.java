package io.quarkiverse.camunda.test;

import java.net.URI;
import java.util.Map;

import io.camunda.client.CamundaClient;
import io.camunda.client.impl.CamundaClientBuilderImpl;
import io.quarkus.test.common.DevServicesContext;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class CamundaTestResource implements QuarkusTestResourceLifecycleManager, DevServicesContext.ContextAware {

    static CamundaClient CLIENT;

    @Override
    public Map<String, String> start() {
        return null;
    }

    @Override
    public void stop() {
        CamundaAssertInitializer.reset();
    }

    @Override
    public void inject(TestInjector testInjector) {
        testInjector.injectIntoFields(CLIENT,
                new TestInjector.AnnotatedAndMatchesType(InjectCamundaClient.class, CamundaClient.class));
    }

    @Override
    public void setIntegrationTestContext(DevServicesContext context) {
        String grpcAddress = context.devServicesProperties().get("quarkiverse.camunda.devservices.test.gateway-address");
        String restAddress = context.devServicesProperties().get("quarkiverse.camunda.devservices.test.rest-address");
        if (grpcAddress != null && restAddress != null) {
            CLIENT = createClient(URI.create(grpcAddress), URI.create(restAddress));
            CamundaAssertInitializer.initialize(CLIENT);
        }
    }

    private static CamundaClient createClient(URI grpcAddress, URI restAddress) {
        return new CamundaClientBuilderImpl()
                .grpcAddress(grpcAddress)
                .restAddress(restAddress)
                .build();
    }
}

package io.quarkiverse.camunda.runtime;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Produces;

import org.jboss.logging.Logger;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.ZeebeClientBuilder;
import io.camunda.zeebe.client.api.JsonMapper;
import io.quarkiverse.camunda.ClientInterceptor;
import io.quarkiverse.camunda.runtime.noop.NoOpClient;

@ApplicationScoped
public class ClientService {

    private static final Logger log = Logger.getLogger(ClientService.class);

    ZeebeClient client;

    public ClientService(RuntimeConfig config, JsonMapper jsonMapper,
            @Any Instance<ClientInterceptor> interceptors) {
        if (config.active()) {
            log.infof("Creating new camunda client for %s", config.client().broker().gatewayAddress());
            ZeebeClientBuilder builder = ClientBuilderFactory.createBuilder(config.client(), jsonMapper);
            interceptors.forEach(x -> builder.withInterceptors(x::interceptCall));
            client = builder.build();
        } else {
            log.infof("Camunda extension is disabled");
            client = new NoOpClient();
        }
    }

    @Produces
    public ZeebeClient client() {
        return client;
    }

}

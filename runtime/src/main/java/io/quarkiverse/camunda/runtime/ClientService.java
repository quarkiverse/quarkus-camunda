package io.quarkiverse.camunda.runtime;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Produces;

import org.apache.hc.client5.http.async.AsyncExecChainHandler;
import org.jboss.logging.Logger;

import io.camunda.client.CamundaClient;
import io.camunda.client.CamundaClientBuilder;
import io.camunda.client.api.JsonMapper;
import io.quarkiverse.camunda.ClientInterceptor;
import io.quarkiverse.camunda.runtime.noop.NoOpClient;

@ApplicationScoped
public class ClientService {

    private static final Logger log = Logger.getLogger(ClientService.class);

    CamundaClient client;

    public ClientService(RuntimeConfig config, JsonMapper jsonMapper,
            @Any Instance<ClientInterceptor> interceptors,
            @Any Instance<AsyncExecChainHandler> chainHandlers) {
        if (config.active()) {
            log.infof("Creating new camunda client for %s", config.client().broker().gatewayAddress());
            CamundaClientBuilder builder = ClientBuilderFactory.createBuilder(config.client(), jsonMapper);
            interceptors.forEach(x -> builder.withInterceptors(x::interceptCall));
            chainHandlers.forEach(builder::withChainHandlers);
            client = builder.build();
        } else {
            log.infof("Camunda extension is disabled");
            client = new NoOpClient();
        }
    }

    @Produces
    public CamundaClient client() {
        return client;
    }

}

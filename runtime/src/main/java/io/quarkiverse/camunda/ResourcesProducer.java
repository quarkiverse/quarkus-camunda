package io.quarkiverse.camunda;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;

import io.camunda.client.api.JsonMapper;
import io.camunda.client.impl.CamundaObjectMapper;
import io.quarkus.arc.DefaultBean;
import io.quarkus.arc.Unremovable;
import io.smallrye.mutiny.infrastructure.Infrastructure;

@Singleton
public class ResourcesProducer {

    @Produces
    @Singleton
    @Unremovable
    @DefaultBean
    public JsonMapper defaultJsonMapper() {
        return new CamundaObjectMapper();
    }

    @Produces
    @Singleton
    @Unremovable
    @DefaultBean
    public ScheduledExecutorService defaultZeebeScheduledExecutorService() {
        return Infrastructure::getDefaultWorkerPool;
    }
}

package io.quarkiverse.camunda.test;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import io.camunda.client.CamundaClient;
import io.camunda.client.api.JsonMapper;
import io.camunda.client.api.response.ProcessInstanceEvent;
import io.camunda.client.api.response.Topology;

@ApplicationScoped
public class TestService {

    @Inject
    CamundaClient client;

    @Inject
    JsonMapper jsonMapper;

    public Topology topology() {
        return client.newTopologyRequest().send().join();
    }

    public ProcessInstanceEvent startProcess(String name, Object parameter) {
        return client.newCreateInstanceCommand()
                .bpmnProcessId(name)
                .latestVersion()
                .variables(parameter)
                .send().join();

    }

    public String toJson(Object value) {
        return jsonMapper.toJson(value);
    }
}

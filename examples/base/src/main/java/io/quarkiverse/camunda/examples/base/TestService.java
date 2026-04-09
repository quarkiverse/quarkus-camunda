package io.quarkiverse.camunda.examples.base;

import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestService {

    public String getParam() {
        return UUID.randomUUID().toString();
    }
}

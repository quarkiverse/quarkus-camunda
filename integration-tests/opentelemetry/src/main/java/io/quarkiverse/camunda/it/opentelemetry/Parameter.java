package io.quarkiverse.camunda.it.opentelemetry;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Parameter {

    public String name;

    public String message;

    @Override
    public String toString() {
        return "Parameter{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

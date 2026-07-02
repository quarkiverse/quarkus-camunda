package io.quarkiverse.camunda.examples.base;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Parameter {

    public String data;

    public String info;

    @Override
    public String toString() {
        return "Parameter{" +
                "data='" + data + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
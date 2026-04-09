package io.quarkiverse.camunda.it.bpmn.parameters;

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
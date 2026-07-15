package io.quarkiverse.camunda.test;

import java.io.InputStream;
import java.util.Map;

import jakarta.enterprise.inject.Produces;

import io.camunda.client.api.JsonMapper;
import io.camunda.client.impl.CamundaObjectMapper;
import io.quarkus.logging.Log;

public class CustomJsonMapperProducer {

    @Produces
    public JsonMapper create() {
        return new JsonMapper() {

            private CamundaObjectMapper mapper = new CamundaObjectMapper();

            @Override
            public <T> T fromJson(String json, Class<T> typeClass) {
                Log.infof("fromJson %s, %s", json, typeClass);
                return mapper.fromJson(json, typeClass);
            }

            @Override
            public Map<String, Object> fromJsonAsMap(String json) {
                Log.infof("fromJsonAsMap %s", json);
                return mapper.fromJsonAsMap(json);
            }

            @Override
            public Map<String, String> fromJsonAsStringMap(String json) {
                Log.infof("fromJsonAsStringMap %s", json);
                return mapper.fromJsonAsStringMap(json);
            }

            @Override
            public String toJson(Object value) {
                Log.infof("toJson %s", value);
                return mapper.toJson(value);
            }

            @Override
            public String validateJson(String propertyName, String jsonInput) {
                Log.infof("validateJson %s, %s", propertyName, jsonInput);
                return mapper.validateJson(propertyName, jsonInput);
            }

            @Override
            public String validateJson(String propertyName, InputStream jsonInput) {
                Log.infof("validateJson %s, <InputStream>", propertyName);
                return mapper.validateJson(propertyName, jsonInput);
            }
        };
    }
}

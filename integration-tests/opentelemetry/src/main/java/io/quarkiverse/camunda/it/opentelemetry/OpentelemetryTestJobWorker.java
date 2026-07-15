package io.quarkiverse.camunda.it.opentelemetry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.camunda.client.api.response.ActivatedJob;
import io.quarkiverse.camunda.JobWorker;
import io.quarkiverse.camunda.Variable;
import io.quarkiverse.camunda.VariablesAsType;

public class OpentelemetryTestJobWorker {

    private static final Logger log = LoggerFactory.getLogger(OpentelemetryTestJobWorker.class);

    @JobWorker(type = "test")
    public Parameter openTelemetryTestMethod(ActivatedJob job, @VariablesAsType Parameter p, @Variable String name,
            @Variable String message) {
        log.info("Job: {}, Parameter: {}, name: {}, message: {}", job, p, name, message);
        p.message = "Ok";
        return p;
    }

}

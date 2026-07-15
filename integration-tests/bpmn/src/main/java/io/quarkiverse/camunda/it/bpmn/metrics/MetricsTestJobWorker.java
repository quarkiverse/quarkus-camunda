package io.quarkiverse.camunda.it.bpmn.metrics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.camunda.client.api.response.ActivatedJob;
import io.quarkiverse.camunda.JobWorker;
import io.quarkiverse.camunda.VariablesAsType;

public class MetricsTestJobWorker {

    private static final Logger log = LoggerFactory.getLogger(MetricsTestJobWorker.class);

    @JobWorker(type = "metrics_test")
    public MetricsTestParameter ok(ActivatedJob job, @VariablesAsType MetricsTestParameter p) {
        log.info("Job: {}, Parameter: {}", job, p);
        p.message = "Ok";
        return p;
    }

}

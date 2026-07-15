package io.quarkiverse.camunda.it.docker.sayhello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.camunda.client.api.response.ActivatedJob;
import io.camunda.client.api.worker.JobClient;
import io.quarkiverse.camunda.JobWorker;
import io.quarkiverse.camunda.VariablesAsType;

public class SayHelloJobWorker {

    private static final Logger log = LoggerFactory.getLogger(SayHelloJobWorker.class);

    @JobWorker(type = "hello_task")
    static public SayHelloParameter sayHello(JobClient client, ActivatedJob job, @VariablesAsType SayHelloParameter p) {
        log.info("Job: {}, Parameter: {}", job, p);
        p.message = "Hello " + p.name;
        return p;
    }
}

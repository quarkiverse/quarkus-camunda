package io.quarkiverse.camunda.it.bpmn.errors;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.quarkiverse.camunda.BpmnError;
import io.quarkiverse.camunda.JobWorker;

public class ErrorsJobWorker {

    @JobWorker(type = "throw-zeebe-error")
    public void throwZeebeError() {
        throw new BpmnError("error-code", "error-message");
    }

    @JobWorker(type = "throw-runtime-exception")
    public void throwRuntimeException() {
        throw new RuntimeException("error-code");
    }

    @JobWorker(type = "fail", autoComplete = false)
    public void fail(final JobClient client, final ActivatedJob job) {
        client.newFailCommand(job.getKey())
                .retries(job.getRetries() - 1)
                .errorMessage("error message")
                .send().join();
    }
}

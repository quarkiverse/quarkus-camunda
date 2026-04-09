package io.quarkiverse.camunda;

import java.util.List;

import io.quarkiverse.camunda.runtime.JobWorkerMetadata;
import io.quarkus.builder.item.SimpleBuildItem;

public final class WorkersBuildItem extends SimpleBuildItem {

    final List<JobWorkerMetadata> workers;

    public WorkersBuildItem(List<JobWorkerMetadata> workers) {
        this.workers = workers;
    }

    public List<JobWorkerMetadata> getWorkers() {
        return workers;
    }

}

package io.quarkiverse.camunda;

import java.util.List;

import io.quarkiverse.camunda.runtime.JobWorkerMetadata;
import io.quarkus.builder.item.SimpleBuildItem;

public final class CamundaWorkersBuildItem extends SimpleBuildItem {

    final List<JobWorkerMetadata> workers;

    public CamundaWorkersBuildItem(List<JobWorkerMetadata> workers) {
        this.workers = workers;
    }

    public List<JobWorkerMetadata> getWorkers() {
        return workers;
    }

}

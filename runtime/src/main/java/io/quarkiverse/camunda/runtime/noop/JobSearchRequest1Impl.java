package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.JobFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.JobSearchRequest;
import io.camunda.client.api.search.response.Job;
import io.camunda.client.api.search.sort.JobSort;

public class JobSearchRequest1Impl extends AbstractFinalSearchRequestStep<Job> implements JobSearchRequest {

    @Override
    public JobSearchRequest filter(JobFilter value) {
        return this;
    }

    @Override
    public JobSearchRequest filter(Consumer<JobFilter> fn) {
        return this;
    }

    @Override
    public JobSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public JobSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public JobSearchRequest sort(JobSort value) {
        return this;
    }

    @Override
    public JobSearchRequest sort(Consumer<JobSort> fn) {
        return this;
    }
}

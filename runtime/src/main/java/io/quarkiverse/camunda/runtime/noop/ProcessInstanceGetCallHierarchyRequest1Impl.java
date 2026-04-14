package io.quarkiverse.camunda.runtime.noop;

import java.util.List;

import io.camunda.client.api.fetch.ProcessInstanceGetCallHierarchyRequest;
import io.camunda.client.api.search.response.ProcessInstanceCallHierarchyEntryResponse;

public class ProcessInstanceGetCallHierarchyRequest1Impl extends AbstractStep<List<ProcessInstanceCallHierarchyEntryResponse>>
        implements ProcessInstanceGetCallHierarchyRequest {
}

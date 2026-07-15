package io.quarkiverse.camunda.runtime.noop;

import java.util.List;

import io.camunda.client.api.search.request.ProcessInstanceSequenceFlowsRequest;
import io.camunda.client.api.search.response.ProcessInstanceSequenceFlow;

public class ProcessInstanceSequenceFlowsRequest1Impl extends AbstractStep<List<ProcessInstanceSequenceFlow>>
        implements ProcessInstanceSequenceFlowsRequest {
}

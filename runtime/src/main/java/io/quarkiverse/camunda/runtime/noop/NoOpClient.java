package io.quarkiverse.camunda.runtime.noop;

import java.time.OffsetDateTime;

import io.camunda.client.CamundaClient;
import io.camunda.client.CamundaClientConfiguration;
import io.camunda.client.api.command.ActivateAdHocSubProcessActivitiesCommandStep1;
import io.camunda.client.api.command.ActivateJobsCommandStep1;
import io.camunda.client.api.command.AssignClientToGroupCommandStep1;
import io.camunda.client.api.command.AssignClientToTenantCommandStep1;
import io.camunda.client.api.command.AssignGroupToTenantCommandStep1;
import io.camunda.client.api.command.AssignMappingRuleToGroupStep1;
import io.camunda.client.api.command.AssignMappingRuleToTenantCommandStep1;
import io.camunda.client.api.command.AssignRoleToClientCommandStep1;
import io.camunda.client.api.command.AssignRoleToGroupCommandStep1;
import io.camunda.client.api.command.AssignRoleToMappingRuleCommandStep1;
import io.camunda.client.api.command.AssignRoleToTenantCommandStep1;
import io.camunda.client.api.command.AssignRoleToUserCommandStep1;
import io.camunda.client.api.command.AssignUserTaskCommandStep1;
import io.camunda.client.api.command.AssignUserToGroupCommandStep1;
import io.camunda.client.api.command.AssignUserToTenantCommandStep1;
import io.camunda.client.api.command.BroadcastSignalCommandStep1;
import io.camunda.client.api.command.CancelBatchOperationStep1;
import io.camunda.client.api.command.CancelProcessInstanceCommandStep1;
import io.camunda.client.api.command.CompleteJobCommandStep1;
import io.camunda.client.api.command.CompleteUserTaskCommandStep1;
import io.camunda.client.api.command.CorrelateMessageCommandStep1;
import io.camunda.client.api.command.CreateAuthorizationCommandStep1;
import io.camunda.client.api.command.CreateBatchOperationCommandStep1;
import io.camunda.client.api.command.CreateDocumentBatchCommandStep1;
import io.camunda.client.api.command.CreateDocumentCommandStep1;
import io.camunda.client.api.command.CreateDocumentLinkCommandStep1;
import io.camunda.client.api.command.CreateGlobalTaskListenerCommandStep1;
import io.camunda.client.api.command.CreateGroupCommandStep1;
import io.camunda.client.api.command.CreateMappingRuleCommandStep1;
import io.camunda.client.api.command.CreateProcessInstanceCommandStep1;
import io.camunda.client.api.command.CreateRoleCommandStep1;
import io.camunda.client.api.command.CreateTenantCommandStep1;
import io.camunda.client.api.command.CreateUserCommandStep1;
import io.camunda.client.api.command.DeleteAuthorizationCommandStep1;
import io.camunda.client.api.command.DeleteDecisionInstanceCommandStep1;
import io.camunda.client.api.command.DeleteDocumentCommandStep1;
import io.camunda.client.api.command.DeleteGlobalTaskListenerCommandStep1;
import io.camunda.client.api.command.DeleteGroupCommandStep1;
import io.camunda.client.api.command.DeleteMappingRuleCommandStep1;
import io.camunda.client.api.command.DeleteProcessInstanceCommandStep1;
import io.camunda.client.api.command.DeleteResourceCommandStep1;
import io.camunda.client.api.command.DeleteRoleCommandStep1;
import io.camunda.client.api.command.DeleteTenantCommandStep1;
import io.camunda.client.api.command.DeleteUserCommandStep1;
import io.camunda.client.api.command.DeployProcessCommandStep1;
import io.camunda.client.api.command.DeployResourceCommandStep1;
import io.camunda.client.api.command.EvaluateConditionalCommandStep1;
import io.camunda.client.api.command.EvaluateDecisionCommandStep1;
import io.camunda.client.api.command.EvaluateExpressionCommandStep1;
import io.camunda.client.api.command.FailJobCommandStep1;
import io.camunda.client.api.command.GloballyScopedClusterVariableCreationCommandStep1;
import io.camunda.client.api.command.GloballyScopedClusterVariableDeletionCommandStep1;
import io.camunda.client.api.command.GloballyScopedClusterVariableUpdateCommandStep1;
import io.camunda.client.api.command.MigrateProcessInstanceCommandStep1;
import io.camunda.client.api.command.ModifyProcessInstanceCommandStep1;
import io.camunda.client.api.command.PinClockCommandStep1;
import io.camunda.client.api.command.PublishMessageCommandStep1;
import io.camunda.client.api.command.ResetClockCommandStep1;
import io.camunda.client.api.command.ResolveIncidentCommandStep1;
import io.camunda.client.api.command.ResolveProcessInstanceIncidentsCommandStep1;
import io.camunda.client.api.command.ResumeBatchOperationStep1;
import io.camunda.client.api.command.SetVariablesCommandStep1;
import io.camunda.client.api.command.StatusRequestStep1;
import io.camunda.client.api.command.StreamJobsCommandStep1;
import io.camunda.client.api.command.SuspendBatchOperationStep1;
import io.camunda.client.api.command.TenantScopedClusterVariableCreationCommandStep1;
import io.camunda.client.api.command.TenantScopedClusterVariableDeletionCommandStep1;
import io.camunda.client.api.command.TenantScopedClusterVariableUpdateCommandStep1;
import io.camunda.client.api.command.ThrowErrorCommandStep1;
import io.camunda.client.api.command.TopologyRequestStep1;
import io.camunda.client.api.command.UnassignClientFromGroupCommandStep1;
import io.camunda.client.api.command.UnassignClientFromTenantCommandStep1;
import io.camunda.client.api.command.UnassignGroupFromTenantCommandStep1;
import io.camunda.client.api.command.UnassignMappingRuleFromGroupStep1;
import io.camunda.client.api.command.UnassignMappingRuleFromTenantCommandStep1;
import io.camunda.client.api.command.UnassignRoleFromClientCommandStep1;
import io.camunda.client.api.command.UnassignRoleFromGroupCommandStep1;
import io.camunda.client.api.command.UnassignRoleFromMappingRuleCommandStep1;
import io.camunda.client.api.command.UnassignRoleFromTenantCommandStep1;
import io.camunda.client.api.command.UnassignRoleFromUserCommandStep1;
import io.camunda.client.api.command.UnassignUserFromGroupCommandStep1;
import io.camunda.client.api.command.UnassignUserFromTenantCommandStep1;
import io.camunda.client.api.command.UnassignUserTaskCommandStep1;
import io.camunda.client.api.command.UpdateAuthorizationCommandStep1;
import io.camunda.client.api.command.UpdateGlobalTaskListenerCommandStep1;
import io.camunda.client.api.command.UpdateGroupCommandStep1;
import io.camunda.client.api.command.UpdateJobCommandStep1;
import io.camunda.client.api.command.UpdateMappingRuleCommandStep1;
import io.camunda.client.api.command.UpdateRetriesJobCommandStep1;
import io.camunda.client.api.command.UpdateRoleCommandStep1;
import io.camunda.client.api.command.UpdateTenantCommandStep1;
import io.camunda.client.api.command.UpdateTimeoutJobCommandStep1;
import io.camunda.client.api.command.UpdateUserCommandStep1;
import io.camunda.client.api.command.UpdateUserTaskCommandStep1;
import io.camunda.client.api.fetch.AuditLogGetRequest;
import io.camunda.client.api.fetch.AuthorizationGetRequest;
import io.camunda.client.api.fetch.AuthorizationsSearchRequest;
import io.camunda.client.api.fetch.BatchOperationGetRequest;
import io.camunda.client.api.fetch.DecisionDefinitionGetRequest;
import io.camunda.client.api.fetch.DecisionDefinitionGetXmlRequest;
import io.camunda.client.api.fetch.DecisionInstanceGetRequest;
import io.camunda.client.api.fetch.DecisionRequirementsGetRequest;
import io.camunda.client.api.fetch.DecisionRequirementsGetXmlRequest;
import io.camunda.client.api.fetch.DocumentContentGetRequest;
import io.camunda.client.api.fetch.ElementInstanceGetRequest;
import io.camunda.client.api.fetch.GlobalTaskListenerGetRequest;
import io.camunda.client.api.fetch.GloballyScopedClusterVariableGetRequest;
import io.camunda.client.api.fetch.GroupGetRequest;
import io.camunda.client.api.fetch.IncidentGetRequest;
import io.camunda.client.api.fetch.MappingRuleGetRequest;
import io.camunda.client.api.fetch.ProcessDefinitionGetFormRequest;
import io.camunda.client.api.fetch.ProcessDefinitionGetRequest;
import io.camunda.client.api.fetch.ProcessDefinitionGetXmlRequest;
import io.camunda.client.api.fetch.ProcessInstanceGetCallHierarchyRequest;
import io.camunda.client.api.fetch.ProcessInstanceGetRequest;
import io.camunda.client.api.fetch.ResourceContentGetRequest;
import io.camunda.client.api.fetch.ResourceGetRequest;
import io.camunda.client.api.fetch.RoleGetRequest;
import io.camunda.client.api.fetch.RolesSearchRequest;
import io.camunda.client.api.fetch.TenantGetRequest;
import io.camunda.client.api.fetch.TenantScopedClusterVariableGetRequest;
import io.camunda.client.api.fetch.UserGetRequest;
import io.camunda.client.api.fetch.UserTaskGetFormRequest;
import io.camunda.client.api.fetch.UserTaskGetRequest;
import io.camunda.client.api.fetch.VariableGetRequest;
import io.camunda.client.api.response.ActivatedJob;
import io.camunda.client.api.response.DocumentReferenceResponse;
import io.camunda.client.api.search.request.AuditLogSearchRequest;
import io.camunda.client.api.search.request.BatchOperationItemSearchRequest;
import io.camunda.client.api.search.request.BatchOperationSearchRequest;
import io.camunda.client.api.search.request.ClientsByGroupSearchRequest;
import io.camunda.client.api.search.request.ClientsByRoleSearchRequest;
import io.camunda.client.api.search.request.ClientsByTenantSearchRequest;
import io.camunda.client.api.search.request.ClusterVariableSearchRequest;
import io.camunda.client.api.search.request.CorrelatedMessageSubscriptionSearchRequest;
import io.camunda.client.api.search.request.DecisionDefinitionSearchRequest;
import io.camunda.client.api.search.request.DecisionInstanceSearchRequest;
import io.camunda.client.api.search.request.DecisionRequirementsSearchRequest;
import io.camunda.client.api.search.request.ElementInstanceSearchRequest;
import io.camunda.client.api.search.request.GlobalTaskListenerSearchRequest;
import io.camunda.client.api.search.request.GroupsByRoleSearchRequest;
import io.camunda.client.api.search.request.GroupsByTenantSearchRequest;
import io.camunda.client.api.search.request.GroupsSearchRequest;
import io.camunda.client.api.search.request.IncidentSearchRequest;
import io.camunda.client.api.search.request.IncidentsByElementInstanceSearchRequest;
import io.camunda.client.api.search.request.IncidentsByProcessInstanceSearchRequest;
import io.camunda.client.api.search.request.JobSearchRequest;
import io.camunda.client.api.search.request.MappingRulesByGroupSearchRequest;
import io.camunda.client.api.search.request.MappingRulesByRoleSearchRequest;
import io.camunda.client.api.search.request.MappingRulesByTenantSearchRequest;
import io.camunda.client.api.search.request.MappingRulesSearchRequest;
import io.camunda.client.api.search.request.MessageSubscriptionSearchRequest;
import io.camunda.client.api.search.request.ProcessDefinitionSearchRequest;
import io.camunda.client.api.search.request.ProcessInstanceSearchRequest;
import io.camunda.client.api.search.request.ProcessInstanceSequenceFlowsRequest;
import io.camunda.client.api.search.request.RolesByGroupSearchRequest;
import io.camunda.client.api.search.request.RolesByTenantSearchRequest;
import io.camunda.client.api.search.request.TenantsSearchRequest;
import io.camunda.client.api.search.request.UserTaskAuditLogSearchRequest;
import io.camunda.client.api.search.request.UserTaskEffectiveVariableSearchRequest;
import io.camunda.client.api.search.request.UserTaskSearchRequest;
import io.camunda.client.api.search.request.UserTaskVariableSearchRequest;
import io.camunda.client.api.search.request.UsersByGroupSearchRequest;
import io.camunda.client.api.search.request.UsersByRoleSearchRequest;
import io.camunda.client.api.search.request.UsersByTenantSearchRequest;
import io.camunda.client.api.search.request.UsersSearchRequest;
import io.camunda.client.api.search.request.VariableSearchRequest;
import io.camunda.client.api.statistics.request.GlobalJobStatisticsRequest;
import io.camunda.client.api.statistics.request.IncidentProcessInstanceStatisticsByDefinitionRequest;
import io.camunda.client.api.statistics.request.IncidentProcessInstanceStatisticsByErrorRequest;
import io.camunda.client.api.statistics.request.JobErrorStatisticsRequest;
import io.camunda.client.api.statistics.request.JobTimeSeriesStatisticsRequest;
import io.camunda.client.api.statistics.request.JobTypeStatisticsRequest;
import io.camunda.client.api.statistics.request.JobWorkerStatisticsRequest;
import io.camunda.client.api.statistics.request.ProcessDefinitionElementStatisticsRequest;
import io.camunda.client.api.statistics.request.ProcessDefinitionInstanceStatisticsRequest;
import io.camunda.client.api.statistics.request.ProcessDefinitionInstanceVersionStatisticsRequest;
import io.camunda.client.api.statistics.request.ProcessDefinitionMessageSubscriptionStatisticsRequest;
import io.camunda.client.api.statistics.request.ProcessInstanceElementStatisticsRequest;
import io.camunda.client.api.statistics.request.UsageMetricsStatisticsRequest;
import io.camunda.client.api.worker.JobWorkerBuilderStep1;
import io.camunda.client.impl.CamundaClientBuilderImpl;

public class NoOpClient implements CamundaClient {

    @Override
    public TopologyRequestStep1 newTopologyRequest() {
        return new TopologyRequestStep1Impl();
    }

    @Override
    public StatusRequestStep1 newStatusRequest() {
        return new StatusRequestStep1Impl();
    }

    @Override
    public CamundaClientConfiguration getConfiguration() {
        return new CamundaClientBuilderImpl();
    }

    @Override
    public void close() {

    }

    @Override
    @Deprecated
    public DeployProcessCommandStep1 newDeployCommand() {
        return null;
    }

    @Override
    public DeployResourceCommandStep1 newDeployResourceCommand() {
        return new DeployResourceCommandStep1Impl();
    }

    @Override
    public CreateProcessInstanceCommandStep1 newCreateInstanceCommand() {
        return new CreateProcessInstanceCommandStep1Impl();
    }

    @Override
    public ModifyProcessInstanceCommandStep1 newModifyProcessInstanceCommand(long processInstanceKey) {
        return new ModifyProcessInstanceCommandStep1Impl();
    }

    @Override
    public MigrateProcessInstanceCommandStep1 newMigrateProcessInstanceCommand(long processInstanceKey) {
        return new MigrateProcessInstanceCommandStep1Impl();
    }

    @Override
    public CancelProcessInstanceCommandStep1 newCancelInstanceCommand(long processInstanceKey) {
        return new CancelProcessInstanceCommandStep1Impl();
    }

    @Override
    public DeleteProcessInstanceCommandStep1 newDeleteProcessInstanceCommand(long processInstanceKey) {
        return new DeleteProcessInstanceCommand1Impl();
    }

    @Override
    public SetVariablesCommandStep1 newSetVariablesCommand(long elementInstanceKey) {
        return new SetVariablesCommandStep1Impl();
    }

    @Override
    public EvaluateDecisionCommandStep1 newEvaluateDecisionCommand() {
        return new EvaluateDecisionCommandStep1Impl();
    }

    @Override
    public PublishMessageCommandStep1 newPublishMessageCommand() {
        return new PublishMessageCommandStep1Impl();
    }

    @Override
    public CorrelateMessageCommandStep1 newCorrelateMessageCommand() {
        return new CorrelateMessageCommand1Impl();
    }

    @Override
    public BroadcastSignalCommandStep1 newBroadcastSignalCommand() {
        return new BroadcastSignalCommandStep1Impl();
    }

    @Override
    public ResolveIncidentCommandStep1 newResolveIncidentCommand(long incidentKey) {
        return new ResolveIncidentCommandStep1Impl();
    }

    @Override
    public ResolveProcessInstanceIncidentsCommandStep1 newResolveProcessInstanceIncidentsCommand(long processInstanceKey) {
        return new ResolveProcessInstanceIncidentsCommand1Impl();
    }

    @Override
    public UpdateRetriesJobCommandStep1 newUpdateRetriesCommand(long jobKey) {
        return new UpdateRetriesJobCommandStep1Impl();
    }

    @Override
    public UpdateRetriesJobCommandStep1 newUpdateRetriesCommand(ActivatedJob job) {
        return new UpdateRetriesJobCommandStep1Impl();
    }

    @Override
    public UpdateTimeoutJobCommandStep1 newUpdateTimeoutCommand(long jobKey) {
        return new UpdateTimeoutJobCommandStep1Impl();
    }

    @Override
    public UpdateTimeoutJobCommandStep1 newUpdateTimeoutCommand(ActivatedJob job) {
        return new UpdateTimeoutJobCommandStep1Impl();
    }

    @Override
    public JobWorkerBuilderStep1 newWorker() {
        return new JobWorkerBuilderStep1Impl();
    }

    @Override
    public DeleteResourceCommandStep1 newDeleteResourceCommand(long resourceKey) {
        return new DeleteResourceCommandStep1Impl();
    }

    @Override
    public CompleteUserTaskCommandStep1 newCompleteUserTaskCommand(long userTaskKey) {
        return new CompleteUserTaskCommandStep1Impl();
    }

    @Override
    public AssignUserTaskCommandStep1 newAssignUserTaskCommand(long userTaskKey) {
        return new AssignUserTaskCommandStep1Impl();
    }

    @Override
    public UpdateUserTaskCommandStep1 newUpdateUserTaskCommand(long userTaskKey) {
        return new UpdateUserTaskCommandStep1Impl();
    }

    @Override
    public UnassignUserTaskCommandStep1 newUnassignUserTaskCommand(long userTaskKey) {
        return new UnassignUserTaskCommandStep1Impl();
    }

    @Override
    public UpdateJobCommandStep1 newUpdateJobCommand(long jobKey) {
        return new UpdateJobCommandStep1Impl();
    }

    @Override
    public UpdateJobCommandStep1 newUpdateJobCommand(ActivatedJob job) {
        return new UpdateJobCommandStep1Impl();
    }

    @Override
    public PinClockCommandStep1 newPinClockCommand() {
        return new PinClockCommandStep1Impl();
    }

    @Override
    public ResetClockCommandStep1 newResetClockCommand() {
        return new ResetClockCommandStep1Impl();
    }

    @Override
    public ProcessDefinitionGetRequest newProcessDefinitionGetRequest(long processDefinitionKey) {
        return new ProcessDefinitionGetRequest1Impl();
    }

    @Override
    public ProcessDefinitionGetXmlRequest newProcessDefinitionGetXmlRequest(long processDefinitionKey) {
        return new ProcessDefinitionGetXmlRequest1Impl();
    }

    @Override
    public ProcessDefinitionGetFormRequest newProcessDefinitionGetFormRequest(long processDefinitionKey) {
        return new ProcessDefinitionGetFormRequest1Impl();
    }

    @Override
    public ProcessDefinitionSearchRequest newProcessDefinitionSearchRequest() {
        return new ProcessDefinitionSearchRequest1Impl();
    }

    @Override
    public ProcessDefinitionElementStatisticsRequest newProcessDefinitionElementStatisticsRequest(long processDefinitionKey) {
        return new ProcessDefinitionElementStatisticsRequest1Impl();
    }

    @Override
    public ProcessInstanceElementStatisticsRequest newProcessInstanceElementStatisticsRequest(long processInstanceKey) {
        return new ProcessInstanceElementStatisticsRequest1Impl();
    }

    @Override
    public ProcessDefinitionMessageSubscriptionStatisticsRequest newProcessDefinitionMessageSubscriptionStatisticsRequest() {
        return new ProcessDefinitionMessageSubscriptionStatisticsRequest1Impl();
    }

    @Override
    public UsageMetricsStatisticsRequest newUsageMetricsRequest(OffsetDateTime startTime, OffsetDateTime endTime) {
        return new UsageMetricsStatisticsRequest1Impl();
    }

    @Override
    public GlobalJobStatisticsRequest newGlobalJobStatisticsRequest(OffsetDateTime from, OffsetDateTime to) {
        return new GlobalJobStatisticsRequest1Impl();
    }

    @Override
    public JobTypeStatisticsRequest newJobTypeStatisticsRequest(OffsetDateTime from, OffsetDateTime to) {
        return new JobTypeStatisticsRequest1Impl();
    }

    @Override
    public JobWorkerStatisticsRequest newJobWorkerStatisticsRequest(OffsetDateTime from, OffsetDateTime to, String jobType) {
        return new JobWorkerStatisticsRequest1Impl();
    }

    @Override
    public JobTimeSeriesStatisticsRequest newJobTimeSeriesStatisticsRequest(OffsetDateTime from, OffsetDateTime to,
            String jobType) {
        return new JobTimeSeriesStatisticsRequest1Impl();
    }

    @Override
    public JobErrorStatisticsRequest newJobErrorStatisticsRequest(OffsetDateTime from, OffsetDateTime to, String jobType) {
        return new JobErrorStatisticsRequest1Impl();
    }

    @Override
    public ProcessInstanceSequenceFlowsRequest newProcessInstanceSequenceFlowsRequest(long processInstanceKey) {
        return new ProcessInstanceSequenceFlowsRequest1Impl();
    }

    @Override
    public ProcessInstanceGetRequest newProcessInstanceGetRequest(long processInstanceKey) {
        return new ProcessInstanceGetRequest1Impl();
    }

    @Override
    public ProcessInstanceSearchRequest newProcessInstanceSearchRequest() {
        return new ProcessInstanceSearchRequest1Impl();
    }

    @Override
    public ElementInstanceSearchRequest newElementInstanceSearchRequest() {
        return new ElementInstanceSearchRequest1Impl();
    }

    @Override
    public ElementInstanceGetRequest newElementInstanceGetRequest(long elementInstanceKey) {
        return new ElementInstanceGetRequest1Impl();
    }

    @Override
    public ActivateAdHocSubProcessActivitiesCommandStep1 newActivateAdHocSubProcessActivitiesCommand(
            String adHocSubProcessInstanceKey) {
        return new ActivateAdHocSubProcessActivitiesCommand1Impl();
    }

    @Override
    public UserTaskSearchRequest newUserTaskSearchRequest() {
        return new UserTaskSearchRequest1Impl();
    }

    @Override
    public DecisionRequirementsSearchRequest newDecisionRequirementsSearchRequest() {
        return new DecisionRequirementsSearchRequest1Impl();
    }

    @Override
    public DecisionDefinitionSearchRequest newDecisionDefinitionSearchRequest() {
        return new DecisionDefinitionSearchRequest1Impl();
    }

    @Override
    public DecisionDefinitionGetRequest newDecisionDefinitionGetRequest(long decisionDefinitionKey) {
        return new DecisionDefinitionGetRequest1Impl();
    }

    @Override
    public DecisionDefinitionGetXmlRequest newDecisionDefinitionGetXmlRequest(long decisionDefinitionKey) {
        return new DecisionDefinitionGetXmlRequest1Impl();
    }

    @Override
    public DecisionInstanceSearchRequest newDecisionInstanceSearchRequest() {
        return new DecisionInstanceSearchRequest1Impl();
    }

    @Override
    public DecisionInstanceGetRequest newDecisionInstanceGetRequest(String decisionInstanceId) {
        return new DecisionInstanceGetRequest1Impl();
    }

    @Override
    public DeleteDecisionInstanceCommandStep1 newDeleteDecisionInstanceCommand(long decisionEvaluationKey) {
        return new DeleteDecisionInstanceCommand1Impl();
    }

    @Override
    public IncidentSearchRequest newIncidentSearchRequest() {
        return new IncidentSearchRequest1Impl();
    }

    @Override
    public IncidentGetRequest newIncidentGetRequest(long incidentKey) {
        return new IncidentGetRequest1Impl();
    }

    @Override
    public CreateRoleCommandStep1 newCreateRoleCommand() {
        return new CreateRoleCommand1Impl();
    }

    @Override
    public RoleGetRequest newRoleGetRequest(String roleId) {
        return new RoleGetRequest1Impl();
    }

    @Override
    public RolesSearchRequest newRolesSearchRequest() {
        return new RolesSearchRequest1Impl();
    }

    @Override
    public UpdateRoleCommandStep1 newUpdateRoleCommand(String roleId) {
        return new UpdateRoleCommand1Impl();
    }

    @Override
    public AssignRoleToMappingRuleCommandStep1 newAssignRoleToMappingRuleCommand() {
        return new AssignRoleToMappingRuleCommand1Impl();
    }

    @Override
    public DeleteRoleCommandStep1 newDeleteRoleCommand(String roleId) {
        return new DeleteRoleCommand1Impl();
    }

    @Override
    public AssignRoleToGroupCommandStep1 newAssignRoleToGroupCommand() {
        return new AssignRoleToGroupCommand1Impl();
    }

    @Override
    public AssignRoleToClientCommandStep1 newAssignRoleToClientCommand() {
        return new AssignRoleToClientCommand1Impl();
    }

    @Override
    public ClientsByRoleSearchRequest newClientsByRoleSearchRequest(String roleId) {
        return new ClientsByRoleSearchRequest1Impl();
    }

    @Override
    public ClientsByGroupSearchRequest newClientsByGroupSearchRequest(String groupId) {
        return new ClientsByGroupSearchRequest1Impl();
    }

    @Override
    public ClientsByTenantSearchRequest newClientsByTenantSearchRequest(String tenantId) {
        return new ClientsByTenantSearchRequest1Impl();
    }

    @Override
    public AssignRoleToTenantCommandStep1 newAssignRoleToTenantCommand() {
        return new AssignRoleToTenantCommand1Impl();
    }

    @Override
    public UnassignRoleFromTenantCommandStep1 newUnassignRoleFromTenantCommand() {
        return new UnassignRoleFromTenantCommand1Impl();
    }

    @Override
    public RolesByTenantSearchRequest newRolesByTenantSearchRequest(String tenantId) {
        return new RolesByTenantSearchRequest1Impl();
    }

    @Override
    public UnassignRoleFromGroupCommandStep1 newUnassignRoleFromGroupCommand() {
        return new UnassignRoleFromGroupCommand1Impl();
    }

    @Override
    public UnassignRoleFromMappingRuleCommandStep1 newUnassignRoleFromMappingRuleCommand() {
        return new UnassignRoleFromMappingRuleCommand1Impl();
    }

    @Override
    public UnassignRoleFromClientCommandStep1 newUnassignRoleFromClientCommand() {
        return new UnassignRoleFromClientCommand1Impl();
    }

    @Override
    public AssignRoleToUserCommandStep1 newAssignRoleToUserCommand() {
        return new AssignRoleToUserCommand1Impl();
    }

    @Override
    public UnassignRoleFromUserCommandStep1 newUnassignRoleFromUserCommand() {
        return new UnassignRoleFromUserCommand1Impl();
    }

    @Override
    public UsersByRoleSearchRequest newUsersByRoleSearchRequest(String roleId) {
        return new UsersByRoleSearchRequest1Impl();
    }

    @Override
    public UsersByTenantSearchRequest newUsersByTenantSearchRequest(String tenantId) {
        return new UsersByTenantSearchRequest1Impl();
    }

    @Override
    public UsersSearchRequest newUsersSearchRequest() {
        return new UsersSearchRequest1Impl();
    }

    @Override
    public CreateGroupCommandStep1 newCreateGroupCommand() {
        return new CreateGroupCommand1Impl();
    }

    @Override
    public UpdateGroupCommandStep1 newUpdateGroupCommand(String groupId) {
        return new UpdateGroupCommand1Impl();
    }

    @Override
    public DeleteGroupCommandStep1 newDeleteGroupCommand(String groupId) {
        return new DeleteGroupCommand1Impl();
    }

    @Override
    public DeleteMappingRuleCommandStep1 newDeleteMappingRuleCommand(String mappingRuleId) {
        return new DeleteMappingRuleCommand1Impl();
    }

    @Override
    public MappingRuleGetRequest newMappingRuleGetRequest(String mappingRuleId) {
        return new MappingRuleGetRequest1Impl();
    }

    @Override
    public AssignUserToGroupCommandStep1 newAssignUserToGroupCommand() {
        return new AssignUserToGroupCommand1Impl();
    }

    @Override
    public UnassignUserFromGroupCommandStep1 newUnassignUserFromGroupCommand() {
        return new UnassignUserFromGroupCommandStep1Impl();
    }

    @Override
    public CreateUserCommandStep1 newCreateUserCommand() {
        return new CreateUserCommand1Impl();
    }

    @Override
    public DeleteUserCommandStep1 newDeleteUserCommand(String username) {
        return new DeleteUserCommand1Impl();
    }

    @Override
    public UpdateUserCommandStep1 newUpdateUserCommand(String username) {
        return new UpdateUserCommand1Impl();
    }

    @Override
    public UserGetRequest newUserGetRequest(String username) {
        return new UserGetRequest1Impl();
    }

    @Override
    public CreateMappingRuleCommandStep1 newCreateMappingRuleCommand() {
        return new CreateMappingRuleCommand1Impl();
    }

    @Override
    public UpdateMappingRuleCommandStep1 newUpdateMappingRuleCommand(String mappingRuleId) {
        return new UpdateMappingRuleCommand1Impl();
    }

    @Override
    public DecisionRequirementsGetXmlRequest newDecisionRequirementsGetXmlRequest(long decisionRequirementsKey) {
        return new DecisionRequirementsGetXmlRequest1Impl();
    }

    @Override
    public DecisionRequirementsGetRequest newDecisionRequirementsGetRequest(long decisionRequirementsKey) {
        return new DecisionRequirementsGetRequest1Impl();
    }

    @Override
    public UserTaskGetFormRequest newUserTaskGetFormRequest(long userTaskKey) {
        return new UserTaskGetFormRequest1Impl();
    }

    @Override
    public UserTaskGetRequest newUserTaskGetRequest(long userTaskKey) {
        return new UserTaskGetRequest1Impl();
    }

    @Override
    public VariableSearchRequest newVariableSearchRequest() {
        return new VariableSearchRequest1Impl();
    }

    @Override
    public VariableGetRequest newVariableGetRequest(long variableKey) {
        return new VariableGetRequest1Impl();
    }

    @Override
    public GloballyScopedClusterVariableCreationCommandStep1 newGloballyScopedClusterVariableCreateRequest() {
        return new GloballyScopedCreateClusterVariable1Impl();
    }

    @Override
    public TenantScopedClusterVariableCreationCommandStep1 newTenantScopedClusterVariableCreateRequest(String tenantId) {
        return new TenantScopedCreateClusterVariable1Impl();
    }

    @Override
    public GloballyScopedClusterVariableUpdateCommandStep1 newGloballyScopedClusterVariableUpdateRequest() {
        return new GloballyScopedUpdateClusterVariable1Impl();
    }

    @Override
    public TenantScopedClusterVariableUpdateCommandStep1 newTenantScopedClusterVariableUpdateRequest(String tenantId) {
        return new TenantScopedUpdateClusterVariable1Impl();
    }

    @Override
    public GloballyScopedClusterVariableDeletionCommandStep1 newGloballyScopedClusterVariableDeleteRequest() {
        return new GloballyScopedClusterVariableDeletionCommandStep1Impl();
    }

    @Override
    public TenantScopedClusterVariableDeletionCommandStep1 newTenantScopedClusterVariableDeleteRequest(String tenantId) {
        return new TenantScopedDeleteClusterVariable1Impl();
    }

    @Override
    public GloballyScopedClusterVariableGetRequest newGloballyScopedClusterVariableGetRequest() {
        return new GloballyScopedClusterVariableGetRequest1Impl();
    }

    @Override
    public TenantScopedClusterVariableGetRequest newTenantScopedClusterVariableGetRequest(String tenantId) {
        return new TenantScopedClusterVariableGetRequest1Impl();
    }

    @Override
    public ClusterVariableSearchRequest newClusterVariableSearchRequest() {
        return new ClusterVariableSearchRequest1Impl();
    }

    @Override
    public UserTaskVariableSearchRequest newUserTaskVariableSearchRequest(long userTaskKey) {
        return new UserTaskVariableSearchRequest1Impl();
    }

    @Override
    public UserTaskEffectiveVariableSearchRequest newUserTaskEffectiveVariableSearchRequest(long userTaskKey) {
        return new UserTaskEffectiveVariableSearchRequest1Impl();
    }

    @Override
    public CreateDocumentCommandStep1 newCreateDocumentCommand() {
        return new CreateDocumentCommand1Impl();
    }

    @Override
    public CreateDocumentBatchCommandStep1 newCreateDocumentBatchCommand() {
        return new CreateDocumentBatchCommand1Impl();
    }

    @Override
    public DocumentContentGetRequest newDocumentContentGetRequest(String documentId) {
        return new DocumentContentGetRequest1Impl();
    }

    @Override
    public DocumentContentGetRequest newDocumentContentGetRequest(DocumentReferenceResponse documentReferenceResponse) {
        return new DocumentContentGetRequest1Impl();
    }

    @Override
    public CreateDocumentLinkCommandStep1 newCreateDocumentLinkCommand(String documentId) {
        return new CreateDocumentLinkCommand1Impl();
    }

    @Override
    public CreateDocumentLinkCommandStep1 newCreateDocumentLinkCommand(DocumentReferenceResponse documentReferenceResponse) {
        return new CreateDocumentLinkCommand1Impl();
    }

    @Override
    public DeleteDocumentCommandStep1 newDeleteDocumentCommand(String documentId) {
        return new DeleteDocumentCommand1Impl();
    }

    @Override
    public DeleteDocumentCommandStep1 newDeleteDocumentCommand(DocumentReferenceResponse documentReferenceResponse) {
        return new DeleteDocumentCommand1Impl();
    }

    @Override
    public CreateTenantCommandStep1 newCreateTenantCommand() {
        return new CreateTenantCommand1Impl();
    }

    @Override
    public UpdateTenantCommandStep1 newUpdateTenantCommand(String tenantId) {
        return new UpdateTenantCommand1Impl();
    }

    @Override
    public TenantGetRequest newTenantGetRequest(String tenantId) {
        return new TenantGetRequest1Impl();
    }

    @Override
    public TenantsSearchRequest newTenantsSearchRequest() {
        return new TenantsSearchRequest1Impl();
    }

    @Override
    public DeleteTenantCommandStep1 newDeleteTenantCommand(String tenantId) {
        return new DeleteTenantCommand1Impl();
    }

    @Override
    public AssignMappingRuleToTenantCommandStep1 newAssignMappingRuleToTenantCommand() {
        return new AssignMappingRuleToTenantCommand1Impl();
    }

    @Override
    public AssignUserToTenantCommandStep1 newAssignUserToTenantCommand() {
        return new AssignUserToTenantCommand1Impl();
    }

    @Override
    public UnassignUserFromTenantCommandStep1 newUnassignUserFromTenantCommand() {
        return new UnassignUserFromTenantCommand1Impl();
    }

    @Override
    public AssignGroupToTenantCommandStep1 newAssignGroupToTenantCommand() {
        return new AssignGroupToTenantCommand1Impl();
    }

    @Override
    public UnassignGroupFromTenantCommandStep1 newUnassignGroupFromTenantCommand() {
        return new UnassignGroupFromTenantCommand1Impl();
    }

    @Override
    public AssignClientToGroupCommandStep1 newAssignClientToGroupCommand() {
        return new AssignClientToGroupCommand1Impl();
    }

    @Override
    public UnassignClientFromGroupCommandStep1 newUnassignClientFromGroupCommand() {
        return new UnassignClientFromGroupCommand1Impl();
    }

    @Override
    public AssignClientToTenantCommandStep1 newAssignClientToTenantCommand() {
        return new AssignClientToTenantCommandStep1Impl();
    }

    @Override
    public UnassignClientFromTenantCommandStep1 newUnassignClientFromTenantCommand() {
        return new UnassignClientFromTenantCommand1Impl();
    }

    @Override
    public UnassignMappingRuleFromTenantCommandStep1 newUnassignMappingRuleFromTenantCommand() {
        return new UnassignMappingRuleFromTenantCommand1Impl();
    }

    @Override
    public CreateAuthorizationCommandStep1 newCreateAuthorizationCommand() {
        return new CreateAuthorizationCommand1Impl();
    }

    @Override
    public AuthorizationGetRequest newAuthorizationGetRequest(long authorizationKey) {
        return new AuthorizationGetRequest1Impl();
    }

    @Override
    public AuthorizationsSearchRequest newAuthorizationSearchRequest() {
        return new AuthorizationsSearchRequest1Impl();
    }

    @Override
    public DeleteAuthorizationCommandStep1 newDeleteAuthorizationCommand(long authorizationKey) {
        return new DeleteAuthorizationCommand1Impl();
    }

    @Override
    public UpdateAuthorizationCommandStep1 newUpdateAuthorizationCommand(long authorizationKey) {
        return new UpdateAuthorizationCommand1Impl();
    }

    @Override
    public CreateBatchOperationCommandStep1 newCreateBatchOperationCommand() {
        return new CreateBatchOperationCommandStep1Impl1<>();
    }

    @Override
    public BatchOperationGetRequest newBatchOperationGetRequest(String batchOperationKey) {
        return new BatchOperationGetRequest1Impl();
    }

    @Override
    public BatchOperationSearchRequest newBatchOperationSearchRequest() {
        return new BatchOperationSearchRequest1Impl();
    }

    @Override
    public CancelBatchOperationStep1 newCancelBatchOperationCommand(String batchOperationKey) {
        return new CancelBatchOperationCommand1Impl();
    }

    @Override
    public SuspendBatchOperationStep1 newSuspendBatchOperationCommand(String batchOperationKey) {
        return new SuspendBatchOperationCommand1Impl();
    }

    @Override
    public ResumeBatchOperationStep1 newResumeBatchOperationCommand(String batchOperationKey) {
        return new ResumeBatchOperationCommand1Impl();
    }

    @Override
    public BatchOperationItemSearchRequest newBatchOperationItemsSearchRequest() {
        return new BatchOperationItemSearchRequest1Impl();
    }

    @Override
    public AssignMappingRuleToGroupStep1 newAssignMappingRuleToGroupCommand() {
        return new AssignMappingRuleToGroupCommand1Impl();
    }

    @Override
    public UnassignMappingRuleFromGroupStep1 newUnassignMappingRuleFromGroupCommand() {
        return new UnassignMappingRuleFromGroupCommand1Impl();
    }

    @Override
    public GroupGetRequest newGroupGetRequest(String groupId) {
        return new GroupGetRequest1Impl();
    }

    @Override
    public GroupsSearchRequest newGroupsSearchRequest() {
        return new GroupSearchRequest1Impl();
    }

    @Override
    public UsersByGroupSearchRequest newUsersByGroupSearchRequest(String groupId) {
        return new UsersByGroupSearchRequest1Impl();
    }

    @Override
    public ProcessInstanceGetCallHierarchyRequest newProcessInstanceGetCallHierarchyRequest(Long processInstanceKey) {
        return new ProcessInstanceGetCallHierarchyRequest1Impl();
    }

    @Override
    public MappingRulesByGroupSearchRequest newMappingRulesByGroupSearchRequest(String groupId) {
        return new MappingRulesByGroupSearchRequest1Impl();
    }

    @Override
    public MappingRulesByRoleSearchRequest newMappingRulesByRoleSearchRequest(String roleId) {
        return new MappingRulesByRoleSearchRequest1Impl();
    }

    @Override
    public MappingRulesByTenantSearchRequest newMappingRulesByTenantSearchRequest(String tenantId) {
        return new MappingRulesByTenantSearchRequest1Impl();
    }

    @Override
    public MappingRulesSearchRequest newMappingRulesSearchRequest() {
        return new MappingRulesSearchRequest1Impl();
    }

    @Override
    public RolesByGroupSearchRequest newRolesByGroupSearchRequest(String groupId) {
        return new RolesByGroupSearchRequest1Impl();
    }

    @Override
    public IncidentsByProcessInstanceSearchRequest newIncidentsByProcessInstanceSearchRequest(long processInstanceKey) {
        return new IncidentsByProcessInstanceSearchRequest1Impl();
    }

    @Override
    public GroupsByRoleSearchRequest newGroupsByRoleSearchRequest(String roleId) {
        return new GroupsByRoleSearchRequest1Impl();
    }

    @Override
    public GroupsByTenantSearchRequest newGroupsByTenantSearchRequest(String tenantId) {
        return new GroupsByTenantSearchRequest1Impl();
    }

    @Override
    public JobSearchRequest newJobSearchRequest() {
        return new JobSearchRequest1Impl();
    }

    @Override
    public MessageSubscriptionSearchRequest newMessageSubscriptionSearchRequest() {
        return new MessageSubscriptionSearchRequest1Impl();
    }

    @Override
    public CorrelatedMessageSubscriptionSearchRequest newCorrelatedMessageSubscriptionSearchRequest() {
        return new CorrelatedMessageSubscriptionSearchRequest1Impl();
    }

    @Override
    public IncidentsByElementInstanceSearchRequest newIncidentsByElementInstanceSearchRequest(long elementInstanceKey) {
        return new IncidentsByElementInstanceSearchRequest1Impl();
    }

    @Override
    public AuditLogGetRequest newAuditLogGetRequest(String auditLogKey) {
        return new AuditLogGetRequest1Impl();
    }

    @Override
    public AuditLogSearchRequest newAuditLogSearchRequest() {
        return new AuditLogSearchRequest1Impl();
    }

    @Override
    public UserTaskAuditLogSearchRequest newUserTaskAuditLogSearchRequest(long userTaskKey) {
        return new UserTaskAuditLogSearchRequest1Impl();
    }

    @Override
    public ProcessDefinitionInstanceStatisticsRequest newProcessDefinitionInstanceStatisticsRequest() {
        return new ProcessDefinitionInstanceStatisticsRequest1Impl();
    }

    @Override
    public ProcessDefinitionInstanceVersionStatisticsRequest newProcessDefinitionInstanceVersionStatisticsRequest(
            String processDefinitionId) {
        return new ProcessDefinitionInstanceVersionStatisticsRequest1Impl();
    }

    @Override
    public EvaluateConditionalCommandStep1 newEvaluateConditionalCommand() {
        return new EvaluateConditionalCommandStep1Impl();
    }

    @Override
    public IncidentProcessInstanceStatisticsByErrorRequest newIncidentProcessInstanceStatisticsByErrorRequest() {
        return new IncidentProcessInstanceStatisticsByErrorRequest1Impl();
    }

    @Override
    public EvaluateExpressionCommandStep1 newEvaluateExpressionCommand() {
        return new EvaluateExpressionCommand1Impl();
    }

    @Override
    public IncidentProcessInstanceStatisticsByDefinitionRequest newIncidentProcessInstanceStatisticsByDefinitionRequest(
            int errorHashCode) {
        return new IncidentProcessInstanceStatisticsByDefinitionRequest1Impl();
    }

    @Override
    public ResourceGetRequest newResourceGetRequest(long resourceKey) {
        return new ResourceGetRequest1Impl();
    }

    @Override
    public ResourceContentGetRequest newResourceContentGetRequest(long resourceKey) {
        return new ResourceContentGetRequest1Impl();
    }

    @Override
    public CreateGlobalTaskListenerCommandStep1 newCreateGlobalTaskListenerRequest() {
        return new CreateGlobalTaskListenerCommand1Impl();
    }

    @Override
    public UpdateGlobalTaskListenerCommandStep1 newUpdateGlobalTaskListenerRequest(String id) {
        return new UpdateGlobalTaskListenerCommand1Impl();
    }

    @Override
    public DeleteGlobalTaskListenerCommandStep1 newDeleteGlobalTaskListenerRequest(String id) {
        return new DeleteGlobalTaskListenerCommand1Impl();
    }

    @Override
    public GlobalTaskListenerGetRequest newGlobalTaskListenerGetRequest(String id) {
        return new GlobalTaskListenerGetRequest1Impl();
    }

    @Override
    public GlobalTaskListenerSearchRequest newGlobalTaskListenerSearchRequest() {
        return new GlobalTaskListenerSearchRequest1Impl();
    }

    @Override
    public CompleteJobCommandStep1 newCompleteCommand(long jobKey) {
        return new CompleteJobCommandStep1Impl();
    }

    @Override
    public CompleteJobCommandStep1 newCompleteCommand(ActivatedJob job) {
        return new CompleteJobCommandStep1Impl();
    }

    @Override
    public FailJobCommandStep1 newFailCommand(long jobKey) {
        return new FailJobCommandStep1Impl();
    }

    @Override
    public FailJobCommandStep1 newFailCommand(ActivatedJob job) {
        return new FailJobCommandStep1Impl();
    }

    @Override
    public ThrowErrorCommandStep1 newThrowErrorCommand(long jobKey) {
        return new ThrowErrorCommandStep1Impl();
    }

    @Override
    public ThrowErrorCommandStep1 newThrowErrorCommand(ActivatedJob job) {
        return new ThrowErrorCommandStep1Impl();
    }

    @Override
    public ActivateJobsCommandStep1 newActivateJobsCommand() {
        return new ActivateJobsCommandStep1Impl();
    }

    @Override
    public StreamJobsCommandStep1 newStreamJobsCommand() {
        return new StreamJobsCommandStep1Impl();
    }

}

package io.quarkiverse.camunda.test;

import java.net.URI;
import java.time.Duration;

import io.camunda.client.api.response.Topology;
import io.camunda.client.impl.CamundaClientBuilderImpl;
import io.camunda.process.test.api.CamundaClientBuilderFactory;
import io.camunda.process.test.impl.runtime.CamundaProcessTestRuntime;

public class QuarkusCamundaProcessTestRuntime implements CamundaProcessTestRuntime {

    private final URI camundaRestApi;
    private final URI camundaGrpcApi;
    private final URI camundaMonitoringApi;

    public QuarkusCamundaProcessTestRuntime(URI camundaRestApi, URI camundaGrpcApi, URI camundaMonitoringApi) {
        this.camundaRestApi = camundaRestApi;
        this.camundaGrpcApi = camundaGrpcApi;
        this.camundaMonitoringApi = camundaMonitoringApi;
    }

    @Override
    public void start() {
        //not needed, quarkus handles this
    }

    @Override
    public URI getCamundaRestApiAddress() {
        return camundaRestApi;
    }

    @Override
    public URI getCamundaGrpcApiAddress() {
        return camundaGrpcApi;
    }

    @Override
    public URI getCamundaMonitoringApiAddress() {
        return camundaMonitoringApi;
    }

    @Override
    public URI getConnectorsRestApiAddress() {
        //Devservices don't provide a Camunda Connector container
        return camundaRestApi;
    }

    @Override
    public CamundaClientBuilderFactory getCamundaClientBuilderFactory() {
        return () -> new CamundaClientBuilderImpl()
                .grpcAddress(getCamundaGrpcApiAddress())
                .restAddress(getConnectorsRestApiAddress());
    }

    @Override
    public Topology waitUntilClusterReady(Duration timeout) {
        //not needed, quarkus devservice handles this
        return null;
    }

    @Override
    public void close() throws Exception {
        //not needed, quarkus handles this
    }
}

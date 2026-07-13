package io.quarkiverse.camunda.runtime;

import java.net.URI;

import org.jboss.logging.Logger;

import io.camunda.client.CredentialsProvider;
import io.camunda.client.api.JsonMapper;
import io.camunda.client.impl.CamundaClientBuilderImpl;
import io.camunda.client.impl.oauth.OAuthCredentialsProviderBuilder;

public class ClientBuilderFactory {

    private static final Logger log = Logger.getLogger(ClientBuilderFactory.class);

    public static CamundaClientBuilderImpl createBuilder(ClientRuntimeConfig config, JsonMapper jsonMapper) {
        CamundaClientBuilderImpl builder = new CamundaClientBuilderImpl();

        builder.grpcAddress(createGatewayAddress(config))
                .restAddress(config.broker().restAddress())
                .defaultTenantId(config.tenant().defaultTenantId())
                .defaultJobWorkerTenantIds(config.tenant().defaultJobWorkerTenantIds())
                .keepAlive(config.broker().keepAlive())
                .defaultJobPollInterval(config.job().pollInterval())
                .defaultJobTimeout(config.job().timeout())
                .defaultJobWorkerMaxJobsActive(config.job().workerMaxJobsActive())
                .defaultJobWorkerName(config.job().workerName())
                .defaultMessageTimeToLive(config.message().timeToLive())
                .numJobWorkerExecutionThreads(config.job().workerExecutionThreads())
                .defaultRequestTimeout(config.job().requestTimeout())
                .credentialsProvider(getCredentialsProvider(config));

        config.security().overrideAuthority().ifPresent(builder::overrideAuthority);
        config.security().certPath().ifPresent(builder::caCertificatePath);
        if (config.broker().useGRPC()) {
            builder.preferRestOverGrpc(false);
            log.info("Using gRPC for Camunda client");
        }
        if (jsonMapper != null) {
            builder.withJsonMapper(jsonMapper);
        }
        return builder;
    }

    private static URI createGatewayAddress(ClientRuntimeConfig config) {
        if (config.cloud().clusterId().isPresent()) {
            return URI.create(String.format("%s.%s.%s:%d",
                    config.cloud().clusterId().get(),
                    config.cloud().region(),
                    config.cloud().baseUrl(),
                    config.cloud().port()));
        }
        return config.broker().gatewayAddress();
    }

    private static CredentialsProvider getCredentialsProvider(ClientRuntimeConfig config) {
        ClientRuntimeConfig.CloudConfig cloud = config.cloud();
        if (cloud.clientId().isPresent() && cloud.clientSecret().isPresent() && cloud.clusterId().isPresent()) {
            OAuthCredentialsProviderBuilder builder = CredentialsProvider.newCredentialsProviderBuilder();
            builder.authorizationServerUrl(cloud.authUrl());
            cloud.clientId().ifPresent(builder::clientId);
            cloud.clientSecret().ifPresent(builder::clientSecret);
            cloud.credentialsCachePath().ifPresent(builder::credentialsCachePath);
            builder.audience(String.format("%s.%s.%s", cloud.clusterId().get(), cloud.region(), cloud.baseUrl()));
            return builder.build();
        }

        ClientRuntimeConfig.OAuthConfig oauth = config.oauth();
        if (oauth.clientId().isPresent() && oauth.clientSecret().isPresent()) {
            OAuthCredentialsProviderBuilder builder = CredentialsProvider.newCredentialsProviderBuilder();
            builder.authorizationServerUrl(oauth.authUrl());
            oauth.clientId().ifPresent(builder::clientId);
            oauth.clientSecret().ifPresent(builder::clientSecret);
            oauth.credentialsCachePath().ifPresent(builder::credentialsCachePath);

            builder.audience(createOauthAudience(config));

            // setup connection timeout
            builder.connectTimeout(oauth.connectTimeout());
            builder.readTimeout(oauth.readTimeout());
            return builder.build();
        }
        return null;
    }

    private static String createOauthAudience(ClientRuntimeConfig config) {
        return config.oauth().tokenAudience().orElseGet(
                () -> config.broker().gatewayAddress().getHost());
    }

}

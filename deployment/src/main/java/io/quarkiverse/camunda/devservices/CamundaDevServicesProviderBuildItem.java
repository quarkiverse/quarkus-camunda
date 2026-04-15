package io.quarkiverse.camunda.devservices;

import io.quarkus.builder.item.SimpleBuildItem;

public final class CamundaDevServicesProviderBuildItem extends SimpleBuildItem {

    public String zeebeInternalUrl;

    public CamundaDevServicesProviderBuildItem(String zeebeInternalUrl) {
        this.zeebeInternalUrl = zeebeInternalUrl;
    }

}

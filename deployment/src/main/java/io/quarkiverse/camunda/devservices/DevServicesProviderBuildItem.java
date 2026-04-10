package io.quarkiverse.camunda.devservices;

import io.quarkus.builder.item.SimpleBuildItem;

public final class DevServicesProviderBuildItem extends SimpleBuildItem {

    public String zeebeInternalUrl;

    public DevServicesProviderBuildItem(String zeebeInternalUrl) {
        this.zeebeInternalUrl = zeebeInternalUrl;
    }

}

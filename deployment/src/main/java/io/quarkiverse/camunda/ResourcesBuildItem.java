package io.quarkiverse.camunda;

import java.util.Collection;

import io.quarkus.builder.item.SimpleBuildItem;

public final class ResourcesBuildItem extends SimpleBuildItem {

    final Collection<String> resources;

    public ResourcesBuildItem(Collection<String> resources) {
        this.resources = resources;
    }

    public Collection<String> getResources() {
        return resources;
    }
}

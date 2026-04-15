package io.quarkiverse.camunda.devservices;

import org.jboss.logging.Logger;

import io.quarkiverse.camunda.CamundaDevServiceBuildTimeConfig;
import io.quarkiverse.camunda.runtime.devmode.JsonRPCService;
import io.quarkiverse.camunda.runtime.devmode.RecordsHandler;
import io.quarkus.deployment.IsDevelopment;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.devui.spi.JsonRPCProvidersBuildItem;
import io.quarkus.devui.spi.page.CardPageBuildItem;
import io.quarkus.devui.spi.page.MenuPageBuildItem;
import io.quarkus.devui.spi.page.Page;
import io.quarkus.vertx.http.deployment.NonApplicationRootPathBuildItem;
import io.quarkus.vertx.http.deployment.RouteBuildItem;
import io.vertx.ext.web.handler.BodyHandler;

public class CamundaDevUIProcessor {

    private static final Logger log = Logger.getLogger(CamundaDevUIProcessor.class);
    private static final String ROOT_PATH = "zeebe";
    private static final String SUB_PATH_RECORDS = "records";

    @BuildStep(onlyIf = IsDevelopment.class)
    public void pages(CamundaDevServiceBuildTimeConfig buildTimeConfig,
            BuildProducer<RouteBuildItem> routes,
            BuildProducer<CardPageBuildItem> cardsProducer,
            NonApplicationRootPathBuildItem nonApplicationRootPathBuildItem,
            BuildProducer<MenuPageBuildItem> menuProducer) {

        if (!buildTimeConfig.devMode().devUIEnabled() || !buildTimeConfig.devService().enabled()) {
            log.debug(
                    "Not starting dev-ui for Zeebe as it has been disabled in the config or dev-service has been not enabled");
            return;
        }

        routes.produce(nonApplicationRootPathBuildItem.routeBuilder()
                .nestedRoute(ROOT_PATH, SUB_PATH_RECORDS)
                .handler(BodyHandler.create())
                .build());

        routes.produce(nonApplicationRootPathBuildItem.routeBuilder()
                .nestedRoute(ROOT_PATH, SUB_PATH_RECORDS)
                .handler(new RecordsHandler())
                .build());

        var dashboard = Page.webComponentPageBuilder()
                .title("Dashboard")
                .icon("font-awesome-solid:diagram-project")
                .componentLink("qwc-zeebe-dashboard.js");

        CardPageBuildItem cardPageBuildItem = new CardPageBuildItem();
        cardPageBuildItem.addPage(dashboard);
        cardsProducer.produce(cardPageBuildItem);

        MenuPageBuildItem menuPageBuildItem = new MenuPageBuildItem();
        menuPageBuildItem.addPage(dashboard);
        menuProducer.produce(menuPageBuildItem);
    }

    @BuildStep
    JsonRPCProvidersBuildItem createJsonRPCService() {
        return new JsonRPCProvidersBuildItem(JsonRPCService.class);
    }

}

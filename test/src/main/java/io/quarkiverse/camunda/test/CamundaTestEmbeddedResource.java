//package io.quarkiverse.camunda.test;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import io.camunda.client.CamundaClient;
//import io.camunda.zeebe.protocol.record.Record;
//import io.quarkus.test.common.DevServicesContext;
//import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
//
//public class CamundaTestEmbeddedResource implements QuarkusTestResourceLifecycleManager, DevServicesContext.ContextAware {
//
//    private static final Logger log = LoggerFactory.getLogger(CamundaTestEmbeddedResource.class);
//
////    public static ZeebeTestEngine ZEEBE_ENGINE;
//
//    static CamundaClient CLIENT;
//
//    @Override
//    public Map<String, String> start() {
////        int randomPort = findFreeRandomPort();
////        log.info("Create Zeebe in-memory engine for test run on random port: {}...", randomPort);
////        ZEEBE_ENGINE = EngineFactory.create(randomPort);
////        ZEEBE_ENGINE.start();
////        String gatewayAddress = ZEEBE_ENGINE.getGatewayAddress();
////        log.info("Zeebe test engine started {}", gatewayAddress);
//        CLIENT = CamundaClient.newClient();
//        return Map.of("quarkus.camunda.client.broker.gateway-address", gatewayAddress);
//    }
//
//    @Override
//    public void stop() {
//        CLIENT.close();
////        ZEEBE_ENGINE.stop();
//    }
//
//    @Override
//    public void inject(TestInjector testInjector) {
//        testInjector.injectIntoFields(CLIENT,
//                new TestInjector.AnnotatedAndMatchesType(InjectCamundaClient.class, CamundaClient.class));
////        testInjector.injectIntoFields(ZEEBE_ENGINE,
////                new TestInjector.AnnotatedAndMatchesType(InjectCamundaTestEngine.class, ZeebeTestEngine.class));
//    }
//
//    private static int findFreeRandomPort() {
//        try (ServerSocket serverSocket = new ServerSocket(0)) {
//            return serverSocket.getLocalPort();
//        } catch (IOException e) {
//            throw new RuntimeException("Zeebe test engine free random port is not available", e);
//        }
//    }
//
//    @Override
//    public void setIntegrationTestContext(DevServicesContext context) {
//        BpmnAssert.initRecordStream(RecordStream.of(new RecordStreamSourceImpl()));
//    }
//
//    public static class RecordStreamSourceImpl implements RecordStreamSource {
//
//        @Override
//        public Iterable<Record<?>> getRecords() {
//            return ZEEBE_ENGINE.getRecordStreamSource().getRecords();
//        }
//    }
//}

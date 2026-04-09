package io.quarkiverse.camunda.runtime.devmode;

import io.grpc.*;
import io.quarkiverse.camunda.ClientInterceptor;

public class JobWorkerReplacementInterceptor implements ClientInterceptor {

    private static volatile Runnable onMessage;

    public static void onMessage(Runnable handler) {
        onMessage = handler;
    }

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions,
            Channel next) {
        onMessage.run();
        return next.newCall(method, callOptions);
    }
}

package io.quarkiverse.camunda.test;

import java.lang.reflect.Method;

import io.camunda.client.CamundaClient;
import io.camunda.process.test.api.CamundaAssert;
import io.camunda.process.test.impl.assertions.CamundaDataSource;

/**
 * {@link CamundaAssert#initialize(CamundaDataSource)} and {@link CamundaAssert#reset()} are
 * package-private, reserved for {@code CamundaProcessTestExtension}. Quarkus loads every
 * dependency artifact in its own classloader, so even a class placed in the same package can't
 * call them directly (it fails with an {@code IllegalAccessError} across classloaders). Reflection
 * with {@code setAccessible(true)} bypasses that language-level check, letting Quarkus dev
 * services wire the assertions to the dev-services-provided {@link CamundaClient} instead.
 */
final class CamundaAssertInitializer {

    private static final Method INITIALIZE = findMethod("initialize", CamundaDataSource.class);
    private static final Method RESET = findMethod("reset");

    private CamundaAssertInitializer() {
    }

    static void initialize(CamundaClient client) {
        invoke(INITIALIZE, new CamundaDataSource(client));
    }

    static void reset() {
        invoke(RESET);
    }

    private static Method findMethod(String name, Class<?>... parameterTypes) {
        try {
            Method method = CamundaAssert.class.getDeclaredMethod(name, parameterTypes);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("CamundaAssert." + name + " is not available", e);
        }
    }

    private static void invoke(Method method, Object... args) {
        try {
            method.invoke(null, args);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Failed to invoke CamundaAssert." + method.getName(), e);
        }
    }
}

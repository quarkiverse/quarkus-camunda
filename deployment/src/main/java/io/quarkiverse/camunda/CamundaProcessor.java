package io.quarkiverse.camunda;

import static io.quarkiverse.camunda.CamundaDotNames.*;
import static io.quarkus.deployment.annotations.ExecutionTime.RUNTIME_INIT;
import static org.jboss.jandex.AnnotationTarget.Kind.METHOD;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jboss.jandex.*;
import org.jboss.jandex.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.camunda.client.api.response.ActivatedJob;
import io.camunda.client.api.worker.JobClient;
import io.quarkiverse.camunda.runtime.*;
import io.quarkiverse.camunda.runtime.health.HealthCheck;
import io.quarkiverse.camunda.runtime.health.TopologyHealthCheck;
import io.quarkiverse.camunda.runtime.metrics.MicrometerMetricsRecorder;
import io.quarkiverse.camunda.runtime.metrics.NoopMetricsRecorder;
import io.quarkiverse.camunda.runtime.tracing.*;
import io.quarkus.arc.Arc;
import io.quarkus.arc.ArcContainer;
import io.quarkus.arc.InjectableBean;
import io.quarkus.arc.InstanceHandle;
import io.quarkus.arc.deployment.*;
import io.quarkus.arc.processor.BeanInfo;
import io.quarkus.arc.processor.BuiltinScope;
import io.quarkus.deployment.Capabilities;
import io.quarkus.deployment.Capability;
import io.quarkus.deployment.GeneratedClassGizmoAdaptor;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.Consume;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.*;
import io.quarkus.deployment.builditem.nativeimage.NativeImageConfigBuildItem;
import io.quarkus.deployment.builditem.nativeimage.NativeImageResourceBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;
import io.quarkus.deployment.metrics.MetricsCapabilityBuildItem;
import io.quarkus.gizmo.*;
import io.quarkus.runtime.metrics.MetricsFactory;
import io.quarkus.runtime.util.HashUtil;
import io.quarkus.smallrye.health.deployment.spi.HealthBuildItem;

public class CamundaProcessor {

    public static final String FEATURE_NAME = "camunda";

    private static final Logger log = LoggerFactory.getLogger(CamundaProcessor.class);

    private static final String JAR_RESOURCE_PROTOCOL = "jar";
    private static final String FILE_RESOURCE_PROTOCOL = "file";
    private static final String DOT_SEPARATOR = ".";

    static final String INVOKER_SUFFIX = "_JobWorkerInvoker";

    static final String NESTED_SEPARATOR = "$_";

    static class TracingEnabled implements BooleanSupplier {

        BuildTimeConfig config;

        @Override
        public boolean getAsBoolean() {
            return config.tracing().enabled();
        }
    }

    @BuildStep(onlyIf = TracingEnabled.class)
    void addTracing(Capabilities capabilities, BuildProducer<AdditionalBeanBuildItem> additionalBeans) {
        if (capabilities.isPresent(Capability.OPENTELEMETRY_TRACER)) {
            additionalBeans.produce(AdditionalBeanBuildItem.unremovableOf(OpenTelemetryTracingRecorder.class));
            additionalBeans.produce(AdditionalBeanBuildItem.unremovableOf(OpenTelemetryClientInterceptor.class));
            additionalBeans.produce(AdditionalBeanBuildItem.unremovableOf(OpenTelemetryHttpChainHandler.class));
        } else {
            additionalBeans.produce(AdditionalBeanBuildItem.unremovableOf(DefaultTracingRecorder.class));
        }
    }

    static class MetricsEnabled implements BooleanSupplier {

        BuildTimeConfig config;

        @Override
        public boolean getAsBoolean() {
            return config.metrics().enabled();
        }
    }

    @BuildStep(onlyIf = MetricsEnabled.class)
    void addMetrics(Optional<MetricsCapabilityBuildItem> metrics, BuildProducer<AdditionalBeanBuildItem> additionalBeans) {
        if (metrics.isPresent()) {
            if (metrics.get().metricsSupported(MetricsFactory.MICROMETER)) {
                additionalBeans.produce(AdditionalBeanBuildItem.unremovableOf(MicrometerMetricsRecorder.class));
                return;
            }
        }
        additionalBeans.produce(AdditionalBeanBuildItem.unremovableOf(NoopMetricsRecorder.class));
    }

    @BuildStep
    AutoAddScopeBuildItem autoAddScopeToJobWorkerClass() {
        return AutoAddScopeBuildItem.builder()
                .anyMethodMatches(m -> !Modifier.isStatic(m.flags()) && m.hasAnnotation(CamundaDotNames.JOB_WORKER))
                .defaultScope(BuiltinScope.SINGLETON)
                .reason("Found non-static job worker execution methods").build();
    }

    @BuildStep
    void collectJobWorkerMethods(BeanArchiveIndexBuildItem beanArchives, CombinedIndexBuildItem indexBuildItem,
            BeanDiscoveryFinishedBuildItem beanDiscovery,
            TransformedAnnotationsBuildItem transformedAnnotations,
            BuildProducer<CamundaJobWorkerMethodItem> jobWorkerMethods) {

        IndexView index = beanArchives.getIndex();

        // First collect static job worker methods
        List<AnnotationInstance> workers = new ArrayList<>(beanArchives.getIndex().getAnnotations(CamundaDotNames.JOB_WORKER));
        for (AnnotationInstance annotationInstance : workers) {
            if (annotationInstance.target().kind() != METHOD) {
                continue;
            }
            MethodInfo method = annotationInstance.target().asMethod();
            if (Modifier.isStatic(method.flags())) {
                jobWorkerMethods.produce(new CamundaJobWorkerMethodItem(null, method, createValue(index, annotationInstance),
                        transformedAnnotations.getAnnotation(method, CamundaDotNames.NON_BLOCKING) != null));
                log.debug("Found job worker static method {} declared on {}", method, method.declaringClass().name());
            }
        }

        // Then collect all business methods annotated with @JobWorker
        for (BeanInfo bean : beanDiscovery.beanStream().classBeans()) {
            collectJobWorkerMethods(index, transformedAnnotations, bean, bean.getTarget().get().asClass(), jobWorkerMethods);
        }
    }

    private void collectJobWorkerMethods(IndexView index, TransformedAnnotationsBuildItem transformedAnnotations, BeanInfo bean,
            ClassInfo beanClass, BuildProducer<CamundaJobWorkerMethodItem> jobWorkOrdersMethods) {

        for (MethodInfo method : beanClass.methods()) {
            if (Modifier.isStatic(method.flags())) {
                // Ignore static methods
                continue;
            }
            AnnotationInstance jobWorkerAnnotation = transformedAnnotations.getAnnotation(method, CamundaDotNames.JOB_WORKER);
            if (jobWorkerAnnotation != null) {
                jobWorkOrdersMethods
                        .produce(new CamundaJobWorkerMethodItem(bean, method, createValue(index, jobWorkerAnnotation),
                                transformedAnnotations.getAnnotation(method, CamundaDotNames.NON_BLOCKING) != null));
                log.debug("Found job worker business method {} declared on {}", method, bean);
            }
        }
    }

    @BuildStep
    void validateScheduledBusinessMethods(List<CamundaJobWorkerMethodItem> workerMethods,
            BuildProducer<ValidationPhaseBuildItem.ValidationErrorBuildItem> validationErrors) {
        List<Throwable> errors = new ArrayList<>();

        for (CamundaJobWorkerMethodItem scheduledMethod : workerMethods) {
            MethodInfo method = scheduledMethod.getMethod();
            if (Modifier.isAbstract(method.flags())) {
                errors.add(new IllegalStateException("@JobWorker method must not be abstract: "
                        + method.declaringClass().name() + "#" + method.name() + "()"));
                continue;
            }
            if (Modifier.isPrivate(method.flags())) {
                errors.add(new IllegalStateException("@JobWorker method must not be private: "
                        + method.declaringClass().name() + "#" + method.name() + "()"));
            }
        }

        if (!errors.isEmpty()) {
            validationErrors.produce(new ValidationPhaseBuildItem.ValidationErrorBuildItem(errors));
        }
    }

    @BuildStep
    public List<UnremovableBeanBuildItem> unRemovableBeans() {
        // Beans annotated with @JobWorker should never be removed
        return List.of(new UnremovableBeanBuildItem(
                new UnremovableBeanBuildItem.BeanClassAnnotationExclusion(CamundaDotNames.JOB_WORKER)));
    }

    @BuildStep
    @Record(RUNTIME_INIT)
    public FeatureBuildItem buildJobWorkerInvokers(Recorder recorder,
            BuildProducer<CamundaWorkersBuildItem> zeebeWorkers,
            List<CamundaJobWorkerMethodItem> workerMethods,
            BuildProducer<GeneratedClassBuildItem> generatedClasses,
            BuildProducer<ReflectiveClassBuildItem> reflectiveClass) {

        ClassOutput classOutput = new GeneratedClassGizmoAdaptor(generatedClasses, new Function<String, String>() {
            @Override
            public String apply(String name) {
                // org/acme/Foo_ScheduledInvoker_run_0000 -> org.acme.Foo
                int idx = name.indexOf(INVOKER_SUFFIX);
                if (idx != -1) {
                    name = name.substring(0, idx);
                }
                if (name.contains(NESTED_SEPARATOR)) {
                    name = name.replace(NESTED_SEPARATOR, "$");
                }
                return name;
            }
        });

        reflectiveClass.produce(
                ReflectiveClassBuildItem
                        .builder(JobWorkerInvoker.class)
                        .constructors(true)
                        .methods(true)
                        .fields(false)
                        .build());

        List<JobWorkerMetadata> metadata = new ArrayList<>();
        for (CamundaJobWorkerMethodItem workerMethod : workerMethods) {

            JobWorkerMetadata meta = new JobWorkerMetadata();
            meta.invokerClass = generateInvoker(workerMethod, classOutput);
            meta.workerValue = workerMethod.getWorker();
            meta.declaringClassName = workerMethod.getMethod().declaringClass().toString();
            meta.methodName = workerMethod.getMethod().name();
            metadata.add(meta);

            reflectiveClass.produce(
                    ReflectiveClassBuildItem
                            .builder(meta.invokerClass)
                            .constructors(true)
                            .methods(false)
                            .fields(false)
                            .build());
        }

        zeebeWorkers.produce(new CamundaWorkersBuildItem(metadata));

        return new FeatureBuildItem(FEATURE_NAME);
    }

    @BuildStep
    void buildZeebeResources(
            BuildTimeConfig config,
            BuildProducer<BeanDefiningAnnotationBuildItem> b,
            BuildProducer<ReflectiveClassBuildItem> reflective,
            BuildProducer<AdditionalBeanBuildItem> additionalBeans,
            BuildProducer<NativeImageResourceBuildItem> resource,
            BuildProducer<ExtensionSslNativeSupportBuildItem> ssl,
            BuildProducer<CamundaResourcesBuildItem> resourcesBuildItem) throws Exception {

        additionalBeans.produce(AdditionalBeanBuildItem.unremovableOf(ClientService.class));
        additionalBeans.produce(AdditionalBeanBuildItem.unremovableOf(ResourcesProducer.class));
        additionalBeans.produce(AdditionalBeanBuildItem.unremovableOf(DefaultJobWorkerExceptionHandler.class));

        resource.produce(new NativeImageResourceBuildItem("client-java.properties"));

        reflective.produce(ReflectiveClassBuildItem.builder(
                "io.camunda.client.impl.response.CreateProcessInstanceResponseImpl",
                "io.camunda.client.impl.response.ActivatedJobImpl",
                "io.camunda.client.impl.response.ActivateJobsResponseImpl",
                "io.camunda.client.impl.response.BrokerInfoImpl",
                "io.camunda.client.impl.response.CancelProcessInstanceResponseImpl",
                "io.camunda.client.impl.response.CompleteJobResponseImpl",
                "io.camunda.client.impl.response.CreateProcessInstanceResponseImpl",
                "io.camunda.client.impl.response.CreateProcessInstanceWithResultResponseImpl",
                "io.camunda.client.impl.response.DecisionImpl",
                "io.camunda.client.impl.response.DecisionRequirementsImpl",
                "io.camunda.client.impl.response.DeploymentEventImpl",
                "io.camunda.client.impl.response.FailJobResponseImpl",
                "io.camunda.client.impl.response.ModifyProcessInstanceResponseImpl",
                "io.camunda.client.impl.response.PartitionInfoImpl",
                "io.camunda.client.impl.response.ProcessImpl",
                "io.camunda.client.impl.response.PublishMessageResponseImpl",
                "io.camunda.client.impl.response.ResolveIncidentResponseImpl",
                "io.camunda.client.impl.response.SetVariablesResponseImpl",
                "io.camunda.client.impl.response.TopologyImpl",
                "io.camunda.client.impl.response.UpdateRetriesJobResponseImpl")
                .constructors(true)
                .methods(true)
                .fields(true)
                .build());

        Collection<String> resources = discoverResources(config.resources());
        if (!resources.isEmpty()) {
            resource.produce(new NativeImageResourceBuildItem(resources.toArray(new String[0])));
        }
        resourcesBuildItem.produce(new CamundaResourcesBuildItem(resources));

        ssl.produce(new ExtensionSslNativeSupportBuildItem(FEATURE_NAME));
    }

    @BuildStep
    IndexDependencyBuildItem indexCamundaClientJava() {
        // camunda-client-java ships no Jandex index of its own; request one so the REST DTO
        // package below can be enumerated instead of hand-listing every response/request class.
        return new IndexDependencyBuildItem("io.camunda", "camunda-client-java");
    }

    @BuildStep
    void registerRestProtocolDtosForReflection(CombinedIndexBuildItem combinedIndex,
            BuildProducer<ReflectiveClassBuildItem> reflective) {
        // Camunda 8.9 clients default to preferRestOverGrpc=true, so Jackson deserializes REST
        // responses into io.camunda.client.protocol.rest.* DTOs via reflection - none of which are
        // registered by camunda-client-java itself, so every one of them needs registering here.
        List<String> classNames = combinedIndex.getIndex().getKnownClasses().stream()
                .map(classInfo -> classInfo.name().toString())
                .filter(name -> name.startsWith("io.camunda.client.protocol.rest."))
                .toList();
        if (!classNames.isEmpty()) {
            reflective.produce(ReflectiveClassBuildItem.builder(classNames.toArray(new String[0]))
                    .constructors(true)
                    .methods(true)
                    .fields(true)
                    .build());
        }
    }

    @BuildStep
    void addHealthCheck(BuildTimeConfig config, BuildProducer<HealthBuildItem> healthChecks) {
        healthChecks.produce(new HealthBuildItem(HealthCheck.class.getName(), config.health().enabled()));
        healthChecks.produce(new HealthBuildItem(TopologyHealthCheck.class.getName(), config.health().enabled()));
    }

    @BuildStep
    @Record(RUNTIME_INIT)
    @Consume(SyntheticBeansRuntimeInitBuildItem.class)
    void runtimeInitConfiguration(Recorder recorder, CamundaWorkersBuildItem workers, CamundaResourcesBuildItem resources) {
        recorder.init(resources.getResources(), workers.getWorkers());
    }

    /**
     * Convert annotation of the JobWorker to JobWorkerValue
     *
     * @param index index of class-path
     * @param ai JobWorker annotation instance
     * @return valur representing the annotation.
     */
    private static JobWorkerValue createValue(IndexView index, AnnotationInstance ai) {
        JobWorkerValue zwv = new JobWorkerValue();
        zwv.enabled = ai.valueWithDefault(index, "enabled").asBoolean();
        zwv.type = ai.valueWithDefault(index, "type").asString();
        zwv.name = ai.valueWithDefault(index, "name").asString();
        zwv.timeout = ai.valueWithDefault(index, "timeout").asInt();
        zwv.maxJobsActive = ai.valueWithDefault(index, "maxJobsActive").asInt();
        zwv.requestTimeout = ai.valueWithDefault(index, "requestTimeout").asLong();
        zwv.pollInterval = ai.valueWithDefault(index, "pollInterval").asLong();
        zwv.fetchVariables = ai.valueWithDefault(index, "fetchVariables").asStringArray();
        zwv.fetchAllVariables = ai.valueWithDefault(index, "fetchAllVariables").asBoolean();
        zwv.autoComplete = ai.valueWithDefault(index, "autoComplete").asBoolean();
        return zwv;
    }

    @BuildStep
    NativeImageConfigBuildItem build() {
        NativeImageConfigBuildItem.Builder builder = NativeImageConfigBuildItem.builder();
        builder.addRuntimeInitializedClass("io.netty.handler.ssl.OpenSsl");
        builder.addRuntimeInitializedClass("io.netty.internal.tcnative.SSL");
        builder.addRuntimeInitializedClass("io.camunda.client.impl.worker.ExponentialBackoff");
        builder.addRuntimeInitializedClass("io.camunda.client.impl.worker.ExponentialBackoffBuilderImpl");
        builder.addRuntimeInitializedClass("io.camunda.client.impl.worker.JobWorkerImpl");
        builder.addRuntimeInitializedClass("io.camunda.client.impl.worker.JobWorkerBuilderImpl");
        return builder.build();
    }

    private Collection<String> discoverResources(BuildTimeConfig.ResourcesConfig resourcesConfig)
            throws IOException, URISyntaxException {
        if (!resourcesConfig.enabled()) {
            return Collections.emptySet();
        }
        String location = resourcesConfig.location();
        LinkedHashSet<String> result = new LinkedHashSet<>();

        location = normalizeLocation(location);
        Enumeration<URL> migrations = Thread.currentThread().getContextClassLoader().getResources(location);
        while (migrations.hasMoreElements()) {
            URL path = migrations.nextElement();
            log.debug("Zeebe resource '{}' using protocol '{}'", path.getPath(), path.getProtocol());
            final Set<String> resources;
            if (JAR_RESOURCE_PROTOCOL.equals(path.getProtocol())) {
                try (final FileSystem fileSystem = initFileSystem(path.toURI())) {
                    resources = getApplicationMigrationsFromPath(location, path);
                }
            } else if (FILE_RESOURCE_PROTOCOL.equals(path.getProtocol())) {
                resources = getApplicationMigrationsFromPath(location, path);
            } else {
                log.warn(
                        "Unsupported URL protocol ''{}'' for path ''{}''. Resource files will not be discovered.",
                        path.getProtocol(), path.getPath());
                resources = null;
            }
            if (resources != null) {
                result.addAll(resources);
            }
        }

        return result;
    }

    private String normalizeLocation(String location) {
        if (location == null) {
            throw new IllegalStateException("Zeebe resource location may not be null.");
        }
        if (!location.endsWith("/")) {
            location += "/";
        }
        return location;
    }

    private Set<String> getApplicationMigrationsFromPath(final String location, final URL path)
            throws IOException, URISyntaxException {
        Path rootPath = Paths.get(path.toURI());

        try (final Stream<Path> pathStream = Files.walk(rootPath)) {
            return pathStream.filter(Files::isRegularFile)
                    .map(it -> Paths.get(location, rootPath.relativize(it).toString()).normalize().toString())
                    // we don't want windows paths here since the paths are going to be used as classpath paths anyway
                    .map(it -> it.replace('\\', '/'))
                    .peek(it -> log.debug("Discovered path: {}", it))
                    .collect(Collectors.toSet());
        }
    }

    private FileSystem initFileSystem(final URI uri) throws IOException {
        final Map<String, String> env = new HashMap<>();
        env.put("create", "true");
        return FileSystems.newFileSystem(uri, env);
    }

    private String generateInvoker(CamundaJobWorkerMethodItem workerMethod, ClassOutput classOutput) {

        BeanInfo bean = workerMethod.getBean();
        MethodInfo method = workerMethod.getMethod();
        boolean isStatic = Modifier.isStatic(method.flags());
        ClassInfo implClazz = isStatic ? method.declaringClass() : bean.getImplClazz();

        String baseName;
        if (implClazz.enclosingClass() != null) {
            baseName = implClazz.enclosingClass().withoutPackagePrefix() + NESTED_SEPARATOR
                    + implClazz.name().withoutPackagePrefix();
        } else {
            baseName = implClazz.name().withoutPackagePrefix();
        }
        StringBuilder sigBuilder = new StringBuilder();
        sigBuilder.append(method.name()).append("_").append(method.returnType().name().toString());
        for (org.jboss.jandex.Type i : method.parameterTypes()) {
            sigBuilder.append(i.name().toString());
        }
        String generatedName = implClazz.name().packagePrefixName()
                + DOT_SEPARATOR
                + baseName
                + INVOKER_SUFFIX + "_" + method.name() + "_"
                + HashUtil.sha1(sigBuilder.toString());

        ClassCreator invokerCreator = ClassCreator.builder().classOutput(classOutput).className(generatedName)
                .superClass(JobWorkerInvoker.class.getName())
                .build();

        MethodCreator invoke = invokerCreator.getMethodCreator("invokeBean", CompletionStage.class, JobClient.class,
                ActivatedJob.class);

        // Use a try-catch block and return failed future if an exception is thrown
        TryBlock tryBlock = invoke.tryBlock();
        CatchBlockCreator catchBlock = tryBlock.addCatch(Throwable.class);
        catchBlock.returnValue(catchBlock.invokeStaticMethod(
                MethodDescriptor.ofMethod(CompletableFuture.class, "failedStage", CompletionStage.class, Throwable.class),
                catchBlock.getCaughtException()));

        String returnTypeStr = DescriptorUtils.typeToString(method.returnType());
        ResultHandle res;
        if (isStatic) {
            if (method.parameterTypes().isEmpty()) {
                res = tryBlock.invokeStaticMethod(
                        MethodDescriptor.ofMethod(implClazz.name().toString(), method.name(), returnTypeStr));
            } else {

                var parameters = createParameters(method, invoke, tryBlock, workerMethod);

                res = tryBlock.invokeStaticMethod(
                        MethodDescriptor.ofMethod(implClazz.name().toString(), method.name(), returnTypeStr,
                                parameters.params),
                        parameters.args);
            }
        } else {
            // InjectableBean<Foo> bean = Arc.container().bean("foo1");
            // InstanceHandle<Foo> handle = Arc.container().instance(bean);
            // handle.get().ping();
            ResultHandle containerHandle = tryBlock
                    .invokeStaticMethod(MethodDescriptor.ofMethod(Arc.class, "container", ArcContainer.class));
            ResultHandle beanHandle = tryBlock.invokeInterfaceMethod(
                    MethodDescriptor.ofMethod(ArcContainer.class, "bean", InjectableBean.class, String.class),
                    containerHandle, tryBlock.load(bean.getIdentifier()));
            ResultHandle instanceHandle = tryBlock.invokeInterfaceMethod(
                    MethodDescriptor.ofMethod(ArcContainer.class, "instance", InstanceHandle.class, InjectableBean.class),
                    containerHandle, beanHandle);
            ResultHandle beanInstanceHandle = tryBlock
                    .invokeInterfaceMethod(MethodDescriptor.ofMethod(InstanceHandle.class, "get", Object.class),
                            instanceHandle);

            if (method.parameterTypes().isEmpty()) {
                res = tryBlock.invokeVirtualMethod(
                        MethodDescriptor.ofMethod(implClazz.name().toString(), method.name(), returnTypeStr),
                        beanInstanceHandle);
            } else {
                var parameters = createParameters(method, invoke, tryBlock, workerMethod);

                res = tryBlock.invokeVirtualMethod(
                        MethodDescriptor.ofMethod(implClazz.name().toString(), method.name(), returnTypeStr, parameters.params),
                        beanInstanceHandle, parameters.args);
            }

            // handle.destroy() - destroy dependent instance afterwards
            if (BuiltinScope.DEPENDENT.is(bean.getScope())) {
                tryBlock.invokeInterfaceMethod(MethodDescriptor.ofMethod(InstanceHandle.class, "destroy", void.class),
                        instanceHandle);
            }
        }

        if (res == null) {
            // If the return type is void then return a new completed stage
            res = tryBlock.invokeStaticMethod(
                    MethodDescriptor.ofMethod(CompletableFuture.class, "completedStage", CompletionStage.class,
                            Object.class),
                    tryBlock.loadNull());
        } else if (method.returnType().name().equals(COMPLETION_STAGE)) {
            // return directly completion stage
        } else if (method.returnType().name().equals(CamundaDotNames.UNI)) {
            // Subscribe to the returned Uni
            res = tryBlock.invokeInterfaceMethod(MethodDescriptor.ofMethod(CamundaDotNames.UNI.toString(),
                    "subscribeAsCompletionStage", CompletableFuture.class), res);
        } else {
            res = tryBlock.invokeStaticMethod(
                    MethodDescriptor.ofMethod(CompletableFuture.class, "completedStage", CompletionStage.class,
                            Object.class),
                    res);
        }

        tryBlock.returnValue(res);

        if (workerMethod.isNonBlocking()) {
            MethodCreator isBlocking = invokerCreator.getMethodCreator("isBlocking", boolean.class);
            isBlocking.returnValue(isBlocking.load(false));
        }

        invokerCreator.close();
        return generatedName.replace('/', '.');
    }

    private static String getAnnotationStringValue(AnnotationValue av, String defaultValue) {
        if (av != null) {
            String tmp = av.asString();
            if (tmp != null && !tmp.isEmpty()) {
                return tmp;
            }
        }
        return defaultValue;
    }

    private Parameters createParameters(MethodInfo method, MethodCreator invoke, TryBlock tryBlock,
            CamundaJobWorkerMethodItem workerMethod) {
        int size = method.parameterTypes().size();

        String[] params = new String[size];
        ResultHandle[] args = new ResultHandle[size];

        Set<String> fetchVariableNames = new HashSet<>();
        boolean fetchVariableAsType = false;

        for (int i = 0; i < size; i++) {
            MethodParameterInfo param = method.parameters().get(i);
            org.jboss.jandex.Type parameterType = method.parameterType(i);
            DotName typeName = parameterType.name();
            params[i] = typeName.toString();
            if (JOB_CLIENT.equals(typeName)) {
                args[i] = invoke.getMethodParam(0);
            } else if (ACTIVATED_JOB.equals(typeName)) {
                args[i] = invoke.getMethodParam(1);
            } else {

                // variable
                AnnotationInstance variable = param.annotation(VARIABLE);
                if (variable != null) {

                    String paramName = getAnnotationStringValue(variable.value(), param.name());
                    fetchVariableNames.add(paramName);
                    String paramClass = parameterType.name().toString();
                    args[i] = tryBlock
                            .invokeSpecialMethod(
                                    MethodDescriptor.ofMethod(JobWorkerInvoker.class, "getVariable",
                                            Object.class,
                                            ActivatedJob.class, String.class, Class.class),
                                    tryBlock.getThis(), invoke.getMethodParam(1), tryBlock.load(paramName),
                                    tryBlock.loadClass(paramClass));

                    continue;
                }

                // variable as type
                AnnotationInstance variableAsType = param.annotation(VARIABLE_AS_TYPE);
                if (variableAsType != null) {
                    fetchVariableAsType = true;
                    String paramClass = parameterType.name().toString();
                    args[i] = tryBlock
                            .invokeSpecialMethod(
                                    MethodDescriptor.ofMethod(JobWorkerInvoker.class, "getVariablesAsType",
                                            Object.class,
                                            ActivatedJob.class, Class.class),
                                    tryBlock.getThis(), invoke.getMethodParam(1),
                                    tryBlock.loadClass(paramClass));
                    continue;
                }

                // custom header
                AnnotationInstance customHeader = param.annotation(CUSTOM_HEADER);
                if (customHeader != null) {
                    if (!STRING.equals(typeName)) {
                        throw new RuntimeException(
                                "Custom header parameter '" + param.name() + "' muss be type of String. CLass '"
                                        + method.declaringClass().name() + "' method '" + method + "'");
                    }

                    String headerName = getAnnotationStringValue(customHeader.value(), param.name());

                    args[i] = tryBlock
                            .invokeSpecialMethod(
                                    MethodDescriptor.ofMethod(JobWorkerInvoker.class, "getCustomHeader",
                                            String.class,
                                            ActivatedJob.class, String.class),
                                    tryBlock.getThis(), invoke.getMethodParam(1), tryBlock.load(headerName));
                    continue;
                }

                // custom headers
                AnnotationInstance customHeaders = param.annotation(CUSTOM_HEADERS);
                if (customHeaders != null) {

                    // Map<String, String>
                    if (!MAP.equals(typeName) || parameterType.kind() != Type.Kind.PARAMETERIZED_TYPE) {
                        throw new RuntimeException(
                                "Custom headers parameter '" + param.name()
                                        + "' muss be type of Map<String, String>. CLass '"
                                        + method.declaringClass().name() + "' method '" + method + "'");
                    }
                    List<Type> mp = parameterType.asParameterizedType().arguments();
                    if (mp.size() != 2 || !STRING.equals(mp.get(0).name()) || !STRING.equals(mp.get(1).name())) {
                        throw new RuntimeException(
                                "Custom headers parameter '" + param.name()
                                        + "' muss be type of Map<String, String>. CLass '"
                                        + method.declaringClass().name() + "' method '" + method + "'");
                    }

                    args[i] = tryBlock
                            .invokeSpecialMethod(
                                    MethodDescriptor.ofMethod(JobWorkerInvoker.class, "getCustomHeaders",
                                            Map.class,
                                            ActivatedJob.class),
                                    tryBlock.getThis(), invoke.getMethodParam(1));
                    continue;
                }

                args[i] = tryBlock.loadNull();
            }
        }

        // fetch all variables when we are using the @VariablesAsType annotation
        if (fetchVariableAsType) {
            workerMethod.getWorker().fetchAllVariables = true;
        }

        // configure job fetch variables base on the parameter and annotation
        if (!workerMethod.getWorker().fetchAllVariables) {
            // merge parameters and annotation variables
            if (!fetchVariableNames.isEmpty()) {
                if (workerMethod.getWorker().fetchVariables.length > 0) {
                    fetchVariableNames.addAll(List.of(workerMethod.getWorker().fetchVariables));
                }
                workerMethod.getWorker().fetchVariables = fetchVariableNames.toArray(new String[0]);
            }
        }

        return new Parameters(params, args);
    }

    private record Parameters(String[] params, ResultHandle[] args) {

    }
}

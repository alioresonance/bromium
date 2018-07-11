package com.hribol.bromium.common.generation.common;

import com.hribol.bromium.core.generation.FunctionRegistry;
import com.hribol.bromium.core.generation.GeneratedFunction;
import com.hribol.bromium.core.generation.GeneratedFunctionFactory;
import com.hribol.bromium.core.generation.GeneratedInvocation;
import com.hribol.bromium.core.generation.GenerationFunctionInformation;
import com.hribol.bromium.core.generation.RegistryInformation;

import java.util.HashSet;
import java.util.Set;

/**
 * A base class for all registries that can generate code based on generation information
 * @param <RegistryInfo> the class which represents the generation information
 * @param <InvocationClass> the class which represents the generated invocation
 * @param <InformationClass> the class which represents the function needed for generation
 */
public class FunctionRegistryBase
        <
                RegistryInfo extends RegistryInformation<InformationClass>,
                InvocationClass extends GeneratedInvocation,
                InformationClass extends GenerationFunctionInformation,
                GeneratedFunctionClass extends GeneratedFunction<RegistryInfo, InvocationClass>
                > implements FunctionRegistry<RegistryInfo> {
    private Set<GeneratedFunction<RegistryInfo, InvocationClass>> functions = new HashSet<>();
    private Set<GeneratedInvocation> invocations = new HashSet<>();
    private GeneratedFunctionFactory<GeneratedFunctionClass, InformationClass> generatedFunctionFactory;

    public FunctionRegistryBase(GeneratedFunctionFactory<GeneratedFunctionClass, InformationClass> generatedFunctionFactory) {
        this.generatedFunctionFactory = generatedFunctionFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String generate(RegistryInfo generationInformation) {
        InformationClass generationFunctionInformation = generationInformation.getGenerationFunctionInformation();
        StringBuilder stringBuilder = new StringBuilder();
        GeneratedFunction<RegistryInfo, InvocationClass> generatedFunction = generatedFunctionFactory.create(generationFunctionInformation);

        if (generatedFunction == null) {
            return "";
        }

        if (!functions.contains(generatedFunction)) {
            stringBuilder.append(generatedFunction.getJavascriptCode());
        }

        InvocationClass recorderFunctionInvocation = generatedFunction.getInvocation(generationInformation);
        if (!invocations.contains(recorderFunctionInvocation)) {
            stringBuilder.append(recorderFunctionInvocation.getJavascriptCode());
        }

        return stringBuilder.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void register(RegistryInfo registerEntry) {
        InformationClass generationFunctionInformation = registerEntry.getGenerationFunctionInformation();

        GeneratedFunction<RegistryInfo, InvocationClass> function = generatedFunctionFactory.create(generationFunctionInformation);

        if (function == null) {
            return;
        }

        if (!functions.contains(function)) {
            functions.add(function);
        }

        InvocationClass invocation = function.getInvocation(registerEntry);
        if (!invocations.contains(invocation)) {
            invocations.add(invocation);
        }
    }
}

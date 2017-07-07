package com.hribol.bromium.core.generation;

import com.hribol.bromium.core.config.WebDriverActionConfiguration;

/**
 * Created by hvrigazov on 01.07.17.
 */
public interface GeneratedFunctionFactory<T extends GeneratedFunction, V extends GenerationFunctionInformation> {
    T create(V generationFunctionInformation);
}
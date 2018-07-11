package com.hribol.bromium.common.parsing;

import com.hribol.bromium.core.TestScenarioSteps;
import com.hribol.bromium.core.config.ApplicationActionConfiguration;
import com.hribol.bromium.core.config.SyntaxDefinitionConfiguration;
import com.hribol.bromium.core.parsing.StepsDumper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.hribol.bromium.core.utils.Constants.EVENT;

/**
 * Dumps recorded steps in a human readable format by given syntax definitions present in
 * {@link ApplicationActionConfiguration}s
 */
public class DslStepsDumper implements StepsDumper {

    private Map<String, ApplicationActionConfiguration> actionConfigurations;

    public DslStepsDumper(Map<String, ApplicationActionConfiguration> actionConfigurations) {
        this.actionConfigurations = actionConfigurations;
    }

    @Override
    public void dump(TestScenarioSteps testScenarioSteps, String outputFile) throws IOException {
        File file = new File(outputFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
            for (Map<String, String> step : testScenarioSteps) {
                List<SyntaxDefinitionConfiguration> syntaxDefinitions =
                        Optional.ofNullable(actionConfigurations.get(step.get(EVENT)))
                                .orElseThrow(() -> new IllegalStateException("The step "
                                        + step + "has an event that is not present in the configuration"
                                        + step.get(EVENT)))
                                .getSyntaxDefinitionConfigurationList();
                StringBuilder lineBuilder = new StringBuilder();
                lineBuilder.append(step.get(EVENT)).append(": ");

                for (SyntaxDefinitionConfiguration syntaxDefinition: syntaxDefinitions) {
                    if (syntaxDefinition.getContent() != null) {
                        lineBuilder
                                .append(syntaxDefinition.getContent())
                                .append(' ');
                    }

                    if (syntaxDefinition.getExposedParameter() != null) {
                        lineBuilder
                                .append(step.get(syntaxDefinition.getExposedParameter()))
                                .append(' ');
                    }
                }

                bufferedWriter.write(lineBuilder.toString());
                bufferedWriter.newLine();
            }
        }

    }
}

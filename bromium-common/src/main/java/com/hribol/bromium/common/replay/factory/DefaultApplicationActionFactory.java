package com.hribol.bromium.common.replay.factory;

import com.hribol.bromium.core.config.ApplicationActionConfiguration;
import com.hribol.bromium.core.config.ApplicationConfiguration;
import com.hribol.bromium.replay.execution.application.ApplicationAction;
import com.hribol.bromium.replay.execution.application.ApplicationActionFactory;
import com.hribol.bromium.replay.execution.factory.WebDriverActionFactory;

import java.util.HashMap;
import java.util.Map;

import static com.hribol.bromium.core.utils.Constants.EVENT;

/**
 * The {@link ApplicationActionFactory} that will be used most frequently.
 * It converts an {@link ApplicationConfiguration} by a given {@link WebDriverActionFactory} and
 * a base url. The url is separate from the other application actions, because
 * we may want to try to test the application running on a different host.
 */
public class DefaultApplicationActionFactory implements ApplicationActionFactory {

    private Map<String, ApplicationActionConfiguration> nameToConfiguration;
    private TestCaseStepToApplicationActionConverter testCaseStepToApplicationActionConverter;

    public DefaultApplicationActionFactory(ApplicationConfiguration applicationConfiguration,
                                           TestCaseStepToApplicationActionConverter testCaseStepToApplicationActionConverter) {
        this.nameToConfiguration = new HashMap<>();

        for (ApplicationActionConfiguration applicationActionConfiguration: applicationConfiguration.getApplicationActionConfigurationList()) {
            nameToConfiguration.put(applicationActionConfiguration.getName(), applicationActionConfiguration);
        }

        this.testCaseStepToApplicationActionConverter = testCaseStepToApplicationActionConverter;
    }

    @Override
    public ApplicationAction create(Map<String, String> testCaseStep, int i) {
        String name = testCaseStep.get(EVENT);
        ApplicationActionConfiguration applicationActionConfiguration = nameToConfiguration.get(name);
        return testCaseStepToApplicationActionConverter.convert(applicationActionConfiguration, testCaseStep, i);
    }
}

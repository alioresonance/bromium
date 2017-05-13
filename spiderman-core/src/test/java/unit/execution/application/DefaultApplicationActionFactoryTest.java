package unit.execution.application;

import com.hribol.spiderman.core.config.ApplicationActionConfiguration;
import com.hribol.spiderman.core.config.ApplicationConfiguration;
import com.hribol.spiderman.core.execution.application.ApplicationAction;
import com.hribol.spiderman.core.execution.application.ApplicationActionFactory;
import com.hribol.spiderman.core.execution.application.DefaultApplicationActionFactory;
import com.hribol.spiderman.core.execution.application.TestCaseStepToApplicationActionConverter;
import com.hribol.spiderman.core.execution.factory.WebDriverActionFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by hvrigazov on 22.04.17.
 */
public class DefaultApplicationActionFactoryTest {

    @Test
    public void canCreatePageLoadingAndOtherActions() {
        String url = "http://tenniskafe.com";
        String actionName = "actionName";
        ApplicationActionConfiguration applicationActionConfiguration = mock(ApplicationActionConfiguration.class);
        when(applicationActionConfiguration.getName()).thenReturn(actionName);

        List<ApplicationActionConfiguration> applicationActionConfigurationList = new ArrayList<>();
        applicationActionConfigurationList.add(applicationActionConfiguration);

        ApplicationConfiguration applicationConfiguration = mock(ApplicationConfiguration.class);
        when(applicationConfiguration.getApplicationActionConfigurationList()).thenReturn(applicationActionConfigurationList);

        TestCaseStepToApplicationActionConverter testCaseStepToApplicationActionConverter =
                mock(TestCaseStepToApplicationActionConverter.class);

        ApplicationAction applicationAction = mock(ApplicationAction.class);

        Map<String, String> testCaseStep = new HashMap<>();
        testCaseStep.put("event", actionName);

        when(testCaseStepToApplicationActionConverter.convert(applicationActionConfiguration, testCaseStep))
                .thenReturn(applicationAction);

        ApplicationActionFactory applicationActionFactory =
                new DefaultApplicationActionFactory(applicationConfiguration, testCaseStepToApplicationActionConverter);

        assertEquals(applicationAction, applicationActionFactory.create(testCaseStep));
    }

}

package com.hribol.spiderman.cli.commands;

import com.hribol.spiderman.replay.ReplayBrowser;
import com.hribol.spiderman.replay.ReplayBrowserConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

/**
 * Created by hvrigazov on 28.04.17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({
        ReplayBrowserConfiguration.class,
        ReplayBrowserConfiguration.Builder.class})
public class ReplayCommandTest {

    private String pathToDriver;
    private String pathToApplicationConfiguration;
    private String pathToSerializedTest;
    private String csvMeasurementsFileName;
    private int timeout;
    private int measurementsPrecisionMilli;
    private String baseURI;

    @Test
    public void ifDriverStartsSuccessfullyEverythingWorks() throws Exception {
        ReplayBrowser replayBrowser = mock(ReplayBrowser.class);
        baseTest(replayBrowser);
        verify(replayBrowser).replay(anyString(), any(), anyString());

    }

    @Test
    public void ifExceptionIsThrownThenItIsLogged() throws Exception {
        ReplayBrowser replayBrowser = mock(ReplayBrowser.class);
        when(replayBrowser.replay(anyString(), any(), anyString())).thenThrow(new IOException("No driver found"));
        baseTest(replayBrowser);
    }

    private void baseTest(ReplayBrowser replayBrowser) throws Exception {
        pathToDriver = "chromedriver";
        pathToApplicationConfiguration = getClass().getResource("/tenniskafe.json").getFile();
        pathToSerializedTest = getClass().getResource("/testCase.json").getFile();
        csvMeasurementsFileName = "measurements.csv";
        timeout = 10;
        measurementsPrecisionMilli = 500;
        baseURI = "http://tenniskafe.com";

        ReplayCommand replayCommand = new ReplayCommand(pathToDriver, pathToApplicationConfiguration,
                pathToSerializedTest, csvMeasurementsFileName, timeout, measurementsPrecisionMilli, baseURI);

        ReplayBrowserConfiguration replayBrowserConfiguration = mock(ReplayBrowserConfiguration.class);
        when(replayBrowserConfiguration.getReplayBrowser()).thenReturn(replayBrowser);
        whenNew(ReplayBrowserConfiguration.class).withAnyArguments().thenReturn(replayBrowserConfiguration);
        replayCommand.run();
    }

}
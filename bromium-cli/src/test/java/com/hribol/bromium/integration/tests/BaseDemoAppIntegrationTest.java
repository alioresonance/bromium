package com.hribol.bromium.integration.tests;

import com.google.common.io.Files;
import com.hribol.bromium.demo.app.DemoApp;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static com.hribol.bromium.integration.tests.TestUtils.SCREEN_SYSTEM_PROPERTY;
import static com.hribol.bromium.integration.tests.TestUtils.extractResource;

/**
 * Created by hvrigazov on 18.07.17.
 */
public abstract class BaseDemoAppIntegrationTest {


    protected static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    protected static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    protected static final PrintStream standardPrintStream = new PrintStream(outContent);
    protected static final PrintStream errorPrintStream = new PrintStream(outContent);


    /**
     * Redirects the standard output so that we can inspect it
     */
    static {
        String shouldShowOutput = System.getProperty("showOutput", "false");
        if (!("true".equals(shouldShowOutput))) {
            System.setOut(standardPrintStream);
            System.setErr(errorPrintStream);
        }
    }

    protected final String screen;

    protected File demoAppResourcesDirectory;
    protected File chromedriverFile;
    protected File configurationFile;
    protected File testCaseFile;
    protected File measurementsFile;
    protected DemoApp demoApp;

    protected File testResourcesDirectory;
    protected File log4j2ConfigurationFile;

    protected String resourceConfigurationPath;
    protected String pathToTestCase;


    public BaseDemoAppIntegrationTest(String resourceConfigurationPath, String pathToTestCase) {
        this(resourceConfigurationPath, pathToTestCase, System.getProperty(SCREEN_SYSTEM_PROPERTY, "1"));
    }

    public BaseDemoAppIntegrationTest(String resourceConfigurationPath, String pathToTestCase, String screen) {
        this.resourceConfigurationPath = resourceConfigurationPath;
        this.pathToTestCase = pathToTestCase;
        this.screen = screen;
    }

    @Before
    public void prepare() throws Exception {
        prepareSystem();
        prepareTestResources();
        prepareDemoApp();
    }

    private void prepareDemoApp() throws Exception {
        demoAppResourcesDirectory = Files.createTempDir();
        demoApp = new DemoApp(demoAppResourcesDirectory);
        demoApp.runOnSeparateThread();
    }

    private void prepareTestResources() throws IOException {
        testResourcesDirectory = Files.createTempDir();

        chromedriverFile = extractResource("chromedriver", testResourcesDirectory);
        if (!chromedriverFile.setExecutable(true)) {
            throw new IllegalStateException("Cannot set chrome driver file to executable");
        }
        configurationFile = extractResource(resourceConfigurationPath, ".brm", testResourcesDirectory);
        measurementsFile = createTempFile("measurements.csv");

        log4j2ConfigurationFile = extractResource("log4j2.xml", testResourcesDirectory);

        Logger.getRootLogger().getLoggerRepository().resetConfiguration();
        PropertyConfigurator.configure(log4j2ConfigurationFile.getAbsolutePath());
    }

    private void prepareSystem() {
    }

    @Test
    abstract public void runTest() throws IOException;

    @After
    public void cleanUp() throws Exception {
        FileUtils.deleteDirectory(testResourcesDirectory);
        demoApp.dispose();
    }

    protected String getOutput() {
        return outContent.toString();
    }

    protected String getOutputOnStdErr() {
        return errContent.toString();
    }

    protected File createTempFile(String filename) throws IOException {
        return File.createTempFile(filename, "", testResourcesDirectory);
    }
}

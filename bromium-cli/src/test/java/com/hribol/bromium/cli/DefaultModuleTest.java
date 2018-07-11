package com.hribol.bromium.cli;

import com.google.common.io.Files;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.ProvisionException;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import com.hribol.bromium.cli.commands.RecordCommand;
import com.hribol.bromium.common.record.RecordBrowser;
import com.hribol.bromium.core.parsing.StepsDumper;
import com.hribol.bromium.core.providers.IOProvider;
import com.hribol.bromium.core.providers.IOURIProvider;
import com.hribol.bromium.core.suite.UbuntuVirtualScreenProcessCreator;
import com.hribol.bromium.core.suppliers.WebDriverSupplier;
import com.hribol.bromium.replay.ReplayBrowser;
import com.hribol.bromium.replay.settings.DriverServiceSupplier;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.filters.ResponseFilter;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static com.hribol.bromium.cli.Main.Commands.RECORD;
import static com.hribol.bromium.cli.Main.Commands.REPLAY;
import static com.hribol.bromium.cli.ParsedOptions.*;
import static com.hribol.bromium.core.DependencyInjectionConstants.CONFIGURATION_INPUT_STREAM;
import static com.hribol.bromium.core.DependencyInjectionConstants.HAR_FILE;
import static com.hribol.bromium.core.DependencyInjectionConstants.MEASUREMENTS_FILE;
import static com.hribol.bromium.core.utils.Constants.HAR_EXTENSION;
import static com.hribol.bromium.integration.tests.TestUtils.CHROMEDRIVER;
import static com.hribol.bromium.integration.tests.TestUtils.Resources;
import static com.hribol.bromium.integration.tests.TestUtils.extractResource;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.remote.BrowserType.CHROME;

/**
 * Tests just check that no provision exception is thrown
 */
public class DefaultModuleTest {

    private final String localhostUrl = "http://localhost:3000";
    private File tempDir;
    private File configurationFile;
    private File chromedriverFile;
    private File caseFile;
    private Process virtualScreenProcess;
    private int screen = 1;
    private int timeout = 10;
    private int precision = 500;

    private String screenString = String.valueOf(screen);
    private String timeoutString = String.valueOf(timeout);
    private String precisionString = String.valueOf(precision);

    @Before
    public void prepareResources() throws IOException {
        tempDir = Files.createTempDir();
        configurationFile = extractResource(Resources.DEMO_CONFIGURATION, ".brm", tempDir);
        chromedriverFile = extractResource(CHROMEDRIVER, tempDir);
        if (!chromedriverFile.setExecutable(true)) {
            throw new IllegalStateException("Cannot set chrome driver file to executable");
        }
        virtualScreenProcess = new UbuntuVirtualScreenProcessCreator().createXvfbProcess(screen);
        caseFile = extractResource(Resources.DYNAMIC_TEST_CASE, tempDir);
    }

    @Test
    public void canCreateRecordBrowserProvider() throws IOException, URISyntaxException {
        String command = RECORD;
        Map<String, Object> opts = new HashMap<>();
        opts.put(DRIVER, chromedriverFile.getAbsolutePath());
        opts.put(APPLICATION, configurationFile.getAbsolutePath());
        opts.put(URL, localhostUrl);
        opts.put(OUTPUT, "output.json");
        opts.put(BROWSER, CHROME);
        opts.put(TIMEOUT, timeoutString);
        opts.put(SCREEN, screenString);
        Module module = new DefaultModule(command, opts);
        Injector injector = Guice.createInjector(module);
        IOURIProvider<RecordBrowser> instance = injector.getInstance(new Key<IOURIProvider<RecordBrowser>>() {});
        RecordBrowser recordBrowser = instance.get();

        // cleanup
        recordBrowser.cleanUp();
    }

    @Test
    public void canCreateRecordCommand() throws IOException, URISyntaxException {
        String command = RECORD;
        Map<String, Object> opts = new HashMap<>();
        opts.put(DRIVER, chromedriverFile.getAbsolutePath());
        opts.put(APPLICATION, configurationFile.getAbsolutePath());
        opts.put(URL, localhostUrl);
        opts.put(OUTPUT, "output.json");
        opts.put(BROWSER, CHROME);
        opts.put(TIMEOUT, timeoutString);
        opts.put(SCREEN, screenString);
        Module module = new DefaultModule(command, opts);
        Injector injector = Guice.createInjector(module);
        RecordCommand instance = injector.getInstance(RecordCommand.class);
    }

    @Test
    public void invalidBrowserThrowsExceptionWhenDriver() throws IOException, URISyntaxException {
        String command = RECORD;
        Map<String, Object> opts = new HashMap<>();
        opts.put(BROWSER, "asd");
        Module module = new DefaultModule(command, opts);
        Injector injector = Guice.createInjector(module);
        try {
            WebDriverSupplier instance = injector.getInstance(WebDriverSupplier.class);
        } catch (ProvisionException e) {
            assertTrue(e.getCause() instanceof BrowserTypeNotSupportedException);
        }
    }

    @Test
    public void invalidBrowserThrowsExceptionWhenDriverServiceSupplier() throws IOException, URISyntaxException {
        String command = RECORD;
        Map<String, Object> opts = new HashMap<>();
        opts.put(BROWSER, "asd");
        Module module = new DefaultModule(command, opts);
        Injector injector = Guice.createInjector(module);
        try {
            DriverServiceSupplier instance = injector.getInstance(DriverServiceSupplier.class);
        } catch (ProvisionException e) {
            assertTrue(e.getCause() instanceof BrowserTypeNotSupportedException);
        }
    }

    @Test
    public void canCreateReplayBrowserProvider() throws IOException, URISyntaxException {
        String command = REPLAY;
        Map<String, Object> opts = new HashMap<>();
        opts.put(BROWSER, CHROME);
        opts.put(DRIVER, chromedriverFile.getAbsolutePath());
        opts.put(APPLICATION, configurationFile.getAbsolutePath());
        opts.put(URL, localhostUrl);
        opts.put(CASE, caseFile.getAbsolutePath());
        opts.put(SCREEN, screenString);
        opts.put(TIMEOUT, timeoutString);
        opts.put(PRECISION, precisionString);

        Module module = new DefaultModule(command, opts);
        Injector injector = Guice.createInjector(module);
        IOURIProvider<ReplayBrowser> instance = injector.getInstance(new Key<IOURIProvider<ReplayBrowser>>() {});

        ReplayBrowser replayBrowser = instance.get();
        replayBrowser.forceCleanUp();
    }

    @Test
    public void ifCommmandIsInvalidExceptionIsThrown() throws IOException {
        String command = "invalid";
        Map<String, Object> opts = new HashMap<>();
        opts.put(BROWSER, CHROME);
        opts.put(DRIVER, chromedriverFile.getAbsolutePath());
        opts.put(APPLICATION, configurationFile.getAbsolutePath());
        opts.put(URL, localhostUrl);
        opts.put(CASE, caseFile.getAbsolutePath());
        opts.put(SCREEN, screenString);
        opts.put(TIMEOUT, timeoutString);
        opts.put(PRECISION, precisionString);
        Module module = new DefaultModule(command, opts);
        Injector injector = Guice.createInjector(module);

        try {
            RequestFilter instance = injector.getInstance(
                    Key.get(new TypeLiteral<IOProvider<RequestFilter>>() {})).get();
        } catch (ProvisionException e) {
            assertTrue(e.getCause() instanceof NoSuchCommandException);
        }
    }

    @Test
    public void ifCommmandIsInvalidResponseExceptionIsThrown() throws IOException, URISyntaxException {
        String command = "invalid";
        Map<String, Object> opts = new HashMap<>();
        opts.put(BROWSER, CHROME);
        opts.put(DRIVER, chromedriverFile.getAbsolutePath());
        opts.put(APPLICATION, configurationFile.getAbsolutePath());
        opts.put(URL, localhostUrl);
        opts.put(CASE, caseFile.getAbsolutePath());
        opts.put(SCREEN, screenString);
        opts.put(TIMEOUT, timeoutString);
        opts.put(PRECISION, precisionString);
        Module module = new DefaultModule(command, opts);
        Injector injector = Guice.createInjector(module);

        try {
            IOProvider<ResponseFilter> instance = injector.getInstance(new Key<IOProvider<ResponseFilter>>() {});
            instance.get();
        } catch (ProvisionException e) {
            assertTrue(e.getCause() instanceof NoSuchCommandException);
        }
    }

    @Test
    public void canSupplyMeasurementsFile() {
        String measurementsFile = "measurements.csv";
        Map<String, Object> opts = new HashMap<>();
        opts.put(MEASUREMENTS, measurementsFile);
        Module module = new DefaultModule(REPLAY, opts);
        Injector injector = Guice.createInjector(module);

        File actualMeasurementsFile = injector.getInstance(Key.get(File.class, Names.named(MEASUREMENTS_FILE)));
        assertEquals(measurementsFile, actualMeasurementsFile.getName());

        File actualHarFile = injector.getInstance(Key.get(File.class, Names.named(HAR_FILE)));
        assertEquals(measurementsFile + HAR_EXTENSION, actualHarFile.getName());
    }

    @Test
    public void canCreateStepsDumper() throws IOException {
        Map<String, Object> opts = new HashMap<>();
        opts.put(APPLICATION, configurationFile.getAbsolutePath());

        Module module = new DefaultModule(RECORD, opts);
        Injector injector = Guice.createInjector(module);

        IOProvider<StepsDumper> ioProvider = injector.getInstance(Key.get(new TypeLiteral<IOProvider<StepsDumper>>() {
        }));

        ioProvider.get();
    }

    @Test
    public void canSupplyConfigurationInputStream() throws IOException {
        Map<String, Object> opts = new HashMap<>();
        opts.put(APPLICATION, configurationFile.getAbsolutePath());

        TypeLiteral<IOProvider<InputStream>> typeLiteral = new TypeLiteral<IOProvider<InputStream>>() {};

        Module module = new DefaultModule(RECORD, opts);
        Injector injector = Guice.createInjector(module);

        IOProvider<InputStream> instance = injector.getInstance(Key.get(typeLiteral, Names.named(CONFIGURATION_INPUT_STREAM)));

        instance.get().close();
    }

    @After
    public void cleanUp() throws IOException {
        FileUtils.deleteDirectory(tempDir);
        virtualScreenProcess.destroy();
    }

}

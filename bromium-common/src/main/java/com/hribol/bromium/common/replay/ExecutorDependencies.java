package com.hribol.bromium.common.replay;

import com.hribol.bromium.common.synchronization.NoHttpRequestsInQueue;
import com.hribol.bromium.common.synchronization.SignalizationBasedEventSynchronizer;
import com.hribol.bromium.core.synchronization.EventSynchronizer;
import com.hribol.bromium.replay.ReplayingState;
import com.hribol.bromium.replay.execution.AutomationResultBuilder;
import com.hribol.bromium.replay.execution.WebDriverActionExecutionException;

import java.io.IOException;
import java.util.Optional;

/**
 * Represents an encapsulation of all the dependencies an {@link ActionExecutor} needs to execute a scenario
 */
public class ExecutorDependencies {
    private static final String DRIVER_EXECUTABLE = "DRIVER_EXECUTABLE";
    private Integer maxRetries;
    private String pathToDriverExecutable;
    private Integer timeout;
    private Integer measurementsPrecisionMilli;
    private String baseURL;
    private AutomationResultBuilder automationResultBuilder;
    private String javascriptInjectionCode;
    private EventSynchronizer eventSynchronizer;
    private String screenToUse;
    private int screenNumber;
    private ReplayingState replayingState;
    private DriverOperations driverOperations;

    public ExecutorDependencies pathToDriverExecutable(String pathToDriverExecutable) {
        this.pathToDriverExecutable = pathToDriverExecutable;
        return this;
    }

    public ExecutorDependencies javascriptInjectionCode(String javascriptInjectionCode) {
        this.javascriptInjectionCode = javascriptInjectionCode;
        return this;
    }

    public ExecutorDependencies timeoutInSeconds(Integer timeout) {
        this.timeout = timeout;
        return this;
    }

    public ExecutorDependencies measurementsPrecisionInMilliseconds(Integer measurementsPrecisionMilli) {
        this.measurementsPrecisionMilli = measurementsPrecisionMilli;
        return this;
    }

    public ExecutorDependencies maxRetries(Integer maxRetries) {
        this.maxRetries = maxRetries;
        return this;
    }

    public ExecutorDependencies baseURL(String baseURL) {
        this.baseURL = baseURL;
        return this;
    }

    public ExecutorDependencies automationResultBuilder(AutomationResultBuilder automationResultBuilder) {
        this.automationResultBuilder = automationResultBuilder;
        return this;
    }

    public ExecutorDependencies eventSynchronizer(EventSynchronizer eventSynchronizer) {
        this.eventSynchronizer = eventSynchronizer;
        return this;
    }

    public String getBaseURL() {
        if (baseURL == null) {
            throw new IllegalStateException("Base URI is not set. Please use the baseURL method");
        }

        return baseURL;
    }

    public String getPathToDriverExecutable() throws IOException {
        pathToDriverExecutable = Optional.ofNullable(pathToDriverExecutable).orElse(System.getenv(DRIVER_EXECUTABLE));
        if (!Optional.ofNullable(pathToDriverExecutable).isPresent()) {
            throw new IOException("Path to driver executable not set. Please either set it using" +
                    " pathToDriverExecutable method or by setting the environment variable" +
                    " DRIVER_EXECUTABLE");
        }

        return pathToDriverExecutable;
    }

    public int getTimeout() {
        if (!Optional.ofNullable(timeout).isPresent()) {
            this.timeout = 10;
        }

        return timeout;
    }

    public int getMeasurementsPrecisionMilli() {
        if (!Optional.ofNullable(measurementsPrecisionMilli).isPresent()) {
            this.measurementsPrecisionMilli = 500;
        }

        return measurementsPrecisionMilli;
    }

    public int getMaxRetries() {
        if (!Optional.ofNullable(maxRetries).isPresent()) {
            this.maxRetries = 50;
        }

        return maxRetries;
    }

    public AutomationResultBuilder getAutomationResultBuilder() {
        if (!Optional.ofNullable(automationResultBuilder).isPresent()) {
            this.automationResultBuilder = new InstanceBasedAutomationResultBuilder();
        }

        return automationResultBuilder;
    }

    public String getJavascriptInjectionCode() {
        return javascriptInjectionCode;
    }

    public EventSynchronizer getEventSynchronizer() {
        if (!Optional.ofNullable(eventSynchronizer).isPresent()) {
            this.eventSynchronizer = new SignalizationBasedEventSynchronizer(getTimeout());
        }

        return eventSynchronizer;
    }

    public NoHttpRequestsInQueue noHttpRequestsInQueue() {
        return new NoHttpRequestsInQueue(getReplayingState(), getEventSynchronizer());
    }

    public WebDriverActionExecutionException webDriverActionExecutionException(String message, Throwable e) {
        return new WebDriverActionExecutionException(message, e, getAutomationResultBuilder());
    }

    public ExecutorDependencies screenToUse(String screen) {
        this.screenToUse = screen;
        return this;
    }

    public String getScreenToUse() {
        return screenToUse;
    }

    public ExecutorDependencies screenNumber(int screen) {
        this.screenNumber = screen;
        return this;
    }

    public int getScreenNumber() {
        return screenNumber;
    }

    public ExecutorDependencies replayingState(ReplayingState replayingState) {
        this.replayingState = replayingState;
        return this;
    }

    public ReplayingState getReplayingState() {
        return replayingState;
    }

    public DriverOperations getDriverOperations() {
        return driverOperations;
    }

    public ExecutorDependencies driverOperations(DriverOperations driverOperations) {
        this.driverOperations = driverOperations;
        return this;
    }
}
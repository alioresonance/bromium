package com.hribol.bromium.demo.app;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;

/**
 * A demo app used in integration tests or as a standalone demo, demonstrating
 * common problems solved with Bromium.
 */
public class DemoApp {

    private final static Logger logger = LoggerFactory.getLogger(DemoApp.class);

    private final File baseOutputDirectory;
    private Server server;
    private String[] resourcesToBeExtractedInDirectory = {
            "ajax.html",
            "dynamic.html",
            "race-http.html",
            "text-field.html",
            "index.html",
            "presence-example.html",
            "text-to-be.html",
            "click-class-by-text.html",
            "set-variable-to-text-of-element-with-css-selector.html",
            "click-id.html",
            "click-name.html",
            "select-value.html",
            "click-id-scroll.html",
            "table-delete-row.html",
            "table-delete-row-by-text.html",
            "table-delete-row-by-index.html",
            "table-recursive.html"
    };

    private int port;

    public DemoApp(File baseOutputDirectory) {
        this.baseOutputDirectory = baseOutputDirectory;
    }

    public void runOnSeparateThread() throws Exception {
        for (String resource: resourcesToBeExtractedInDirectory) {
            InputStream resourceAsStream = this.getClass().getResourceAsStream("/" + resource);
            File outputFile = Paths.get(baseOutputDirectory.getAbsolutePath(), resource).toFile();
            FileOutputStream fileOutputStream =  FileUtils.openOutputStream(outputFile);
            IOUtils.copy(resourceAsStream, fileOutputStream);
        }

        ResourceConfig config = new ResourceConfig();
        config.packages("com.hribol.bromium.demo.app");
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        server = new Server(0);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(baseOutputDirectory.getAbsolutePath());
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setWelcomeFiles(new String[] {
                "index.html"
        });

        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] {
                resourceHandler,
                context
        });
        server.setHandler(handlers);

        server.start();
        this.port = ((ServerConnector) server.getConnectors()[0]).getLocalPort();
        logger.info("Server started on port " + port);

        new Thread(() -> {
            try {
                server.join();
            } catch (InterruptedException e) {
                logger.info("Interrupted!", e);
            }
        }).start();
    }

    public void dispose() throws Exception {
        server.stop();
        for (File file: baseOutputDirectory.listFiles()) {
            file.delete();
        }
    }

    public int getPort() {
        return port;
    }

    public String getBaseUrl() {
        return "http://localhost:" + getPort() + "/";
    }
}

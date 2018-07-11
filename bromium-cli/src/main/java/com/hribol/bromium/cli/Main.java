package com.hribol.bromium.cli;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hribol.bromium.cli.commands.Command;
import com.hribol.bromium.cli.commands.RecordCommand;
import com.hribol.bromium.cli.commands.ReplayCommand;
import org.apache.commons.io.IOUtils;
import org.docopt.Docopt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static com.hribol.bromium.cli.Main.Commands.RECORD;
import static com.hribol.bromium.cli.Main.Commands.REPLAY;

/**
 * Entry point to the application
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static class Commands {
        public static final String RECORD = "record";
        public static final String REPLAY = "replay";
    }

    private static Injector injector;

    public static void main(String[] args) {
        logger.info("Running CLI with arguments {}", Arrays.toString(args));
        try {
            InputStream inputStream = Main.class.getResourceAsStream("/cli-specification.txt");
            String doc = IOUtils.toString(inputStream);
            Docopt docopt = new Docopt(doc);
            Map<String, Object> opts = docopt
                    .withVersion("bromium 0.1")
                    .withHelp(true)
                    .withExit(true)
                    .parse(args);

            Map<String, Supplier<Command>> commandToHandler = getCommands();
            String selectedCommand = commandToHandler
                    .keySet()
                    .stream()
                    .filter(command -> opts.get(command).equals(true))
                    .findAny()
                    // we know that a command must be present because otherwise docopt would have stopped
                    .get();

            injector = Guice.createInjector(new DefaultModule(selectedCommand, opts));
            commandToHandler.get(selectedCommand).get().run();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

    }

    private static Map<String, Supplier<Command>> getCommands() {
        Map<String, Supplier<Command>> map = new HashMap<>();
        map.put(RECORD, () -> injector.getInstance(RecordCommand.class));
        map.put(REPLAY, () -> injector.getInstance(ReplayCommand.class));
        return map;
    }

}

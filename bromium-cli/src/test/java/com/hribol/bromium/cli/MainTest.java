package com.hribol.bromium.cli;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.hribol.bromium.cli.commands.Command;
import com.hribol.bromium.cli.commands.RecordCommand;
import com.hribol.bromium.cli.commands.ReplayCommand;
import org.apache.commons.io.IOUtils;
import org.docopt.DocoptExitException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.io.InputStream;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hvrigazov on 09.05.17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({
        IOUtils.class,
        Main.class,
        Guice.class
})
public class MainTest {


    @Test
    public void dispatchesToRecordCorrectly() throws Exception {
        String[] args = {
                "record",
                "-d", "chromedriver",
                "-a", "/home/hvrigazov/bromium-data/demo-app/configurations/demo.brm",
                "-u", "http://localhost:3000",
                "-o", "bromium-core/src/test/resources/dynamic-testCase.json"
        };

        baseTest(args, RecordCommand.class);
    }

    @Test
    public void dispatchesToReplayCorrectly() throws Exception {
        String[] args = {
                "replay",
                "-d", "chromedriver",
                "-a", "/home/hvrigazov/bromium-data/demo-app/configurations/demo.brm",
                "-u", "http://localhost:3000",
                "-c", "bromium-core/src/test/resources/dynamic-testCase.json",
                "-m", "measurements.csv"
        };

        baseTest(args, ReplayCommand.class);
    }

    private <T extends Command> void baseTest(String[] args, Class<T> klazz) {
        T command = mock(klazz);

        Injector injector = mock(Injector.class);
        PowerMockito.mockStatic(Guice.class);
        when(Guice.createInjector(any(Module.class))).thenReturn(injector);

        when(injector.getInstance(klazz)).thenReturn(command);

        Main.main(args);
        verify(command).run();
    }

    @Test
    public void doesNotDoAnythingIfExceptionIsThrown() throws IOException {
        PowerMockito.mockStatic(IOUtils.class);
        PowerMockito.when(IOUtils.toString(any(InputStream.class))).thenThrow(new IOException("Exception while reading spec"));

        String[] args = {
                "update", "-a", "configuration.json"
        };

        Main.main(args);
    }
//
//    @Rule
//    private ExpectedException expectedException = ExpectedException.none();
//
//    @Test
//    public void commandThatDoesNotExistThrowsDocoptExitException() {
//        expectedException.expect(DocoptExitException.class);
//        String[] args = {
//                "yoyo"
//        };
//        Main.main(args);
//    }

}

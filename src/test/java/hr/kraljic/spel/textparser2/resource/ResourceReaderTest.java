package hr.kraljic.spel.textparser2.resource;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ResourceReaderTest {
    private static final String TEST_FILE_NAME = "/resources/test_resources.txt";
    private static final String TEST_OUT = "Hello world!";

    @Test
    void readFile() throws IOException {
        String out = ResourceReader.readFile(TEST_FILE_NAME);

        assertEquals(TEST_OUT, out);
    }
}
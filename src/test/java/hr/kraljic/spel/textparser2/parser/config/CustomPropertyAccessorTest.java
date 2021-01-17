package hr.kraljic.spel.textparser2.parser.config;

import hr.kraljic.spel.textparser2.SpelParserFactory;
import hr.kraljic.spel.textparser2.parser.SpelParser;
import hr.kraljic.spel.textparser2.resource.ResourceReader;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomPropertyAccessorTest {
    private static final String DATE_TYPE_RESOLVER_TEST_FILE = "/type_resolver/test_date_type_resolver.txt";
    private static final String DATE_TYPE_RESOLVER_OUT = "10.01.2021";
    private static final String DATE_TIME_TYPE_RESOLVER_TEST_FILE = "/type_resolver/test_date_time_type_resolver.txt";
    private static final String DATE_TIME_TYPE_RESOLVER_OUT = "10.01.2021 16:45:13";

    SpelParser spelParser = SpelParserFactory.create();

    @Test
    void test_dateTypeResolver() throws IOException {
        String text = ResourceReader.readFile(DATE_TYPE_RESOLVER_TEST_FILE);
        String out = spelParser.parse(text, Dummy.create());
        System.out.println(out);
        assertEquals(DATE_TYPE_RESOLVER_OUT, out);
    }

    @Test
    void test_dateTimeTypeResolver() throws IOException {
        String text = ResourceReader.readFile(DATE_TIME_TYPE_RESOLVER_TEST_FILE);
        String out = spelParser.parse(text, Dummy.create());
        System.out.println(out);
        assertEquals(DATE_TIME_TYPE_RESOLVER_OUT, out);
    }

    @Data
    static class Dummy {
        private LocalDate date;
        private LocalDateTime dateTime;

        static Dummy create() {
            return new Dummy() {{
                setDate(LocalDate.of(2021, 1, 10));
                setDateTime(LocalDateTime.of(2021, 1, 10, 16, 45, 13));
            }};
        }
    }
}
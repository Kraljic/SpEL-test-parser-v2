package hr.kraljic.spel.textparser2.parser;

import hr.kraljic.spel.textparser2.parser.config.SpelConfig;
import hr.kraljic.spel.textparser2.resource.ResourceReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParserUtilTest {
    private static final String COMMENTS_TEST_FILE = "/parser_util/test_comments.txt";
    private static final String COMMENTS_OUT = "Hello world!\n" +
            "How are you?\n" +
            "This is not a comment!\n";
    private static final String EMPTY_LINES_TEST_FILE = "/parser_util/test_empty_lines.txt";
    private static final String EMPTY_LINES_OUT = "Hello world!\n" +
            "How are you?\n" +
            "This is not empty line!\n" +
            "After this should be one line.\n";

    @Test
    void filterCommentLines() throws IOException {
        String text = ResourceReader.readFile(COMMENTS_TEST_FILE);
        SpelConfig spelConfig = SpelConfig.builder().commentPrefix("#").build();
        String out = ParserUtil.filterCommentLines(text, spelConfig);
        System.out.println(out);
        assertEquals(COMMENTS_OUT, out);
    }

    @Test
    void filterEmptyLines() throws IOException {
        String text = ResourceReader.readFile(EMPTY_LINES_TEST_FILE);
        SpelConfig spelConfig = SpelConfig.builder().filterEmptyLines(true).build();
        String out = ParserUtil.filterEmptyLines(text, spelConfig);
        System.out.println(out);
        assertEquals(EMPTY_LINES_OUT, out);
    }
}
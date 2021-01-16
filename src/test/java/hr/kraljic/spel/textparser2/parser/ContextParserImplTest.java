package hr.kraljic.spel.textparser2.parser;

import hr.kraljic.spel.textparser2.resource.ResourceReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ContextParserImplTest {
    private static final String ROOT_CONTEXT_TEST_FILE = "/context/test_root_context.txt";
    private static final String ROOT_CONTEXT_OUT = "Hello world!";
    private static final String SUB_CONTEXT_TEST_FILE = "/context/test_sub_context.txt";
    private static final String SUB_CONTEXT_OUT = "Hello world!";
    private static final String MULTI_SUB_CONTEXT_TEST_FILE = "/context/test_multi_sub_context.txt";
    private static final Integer MULTI_SUB_CONTEXT_COUNT = 3;
    private static final String RECURSIVE_SUB_CONTEXT_TEST_FILE = "/context/test_recursive_sub_context.txt";
    private static final Integer RECURSIVE_SUB_CONTEXT_NESTED_COUNT = 3;

    ContextParser contextParser = new ContextParserImpl();

    @Test
    void parse_rootContext() throws IOException {
        String file = ResourceReader.readFile(ROOT_CONTEXT_TEST_FILE);
        ContextData data = contextParser.parse(file);
        System.out.println(data);
        assertEquals(ROOT_CONTEXT_OUT, data.getRaw());
    }

    @Test
    void parse_subContext() throws IOException {
        String file = ResourceReader.readFile(SUB_CONTEXT_TEST_FILE);
        ContextData data = contextParser.parse(file);
        System.out.println(data);

        assertEquals(SUB_CONTEXT_OUT, data.getSubContexts().get(0));
    }

    @Test
    void parse_multiSubContext() throws IOException {
        String file = ResourceReader.readFile(MULTI_SUB_CONTEXT_TEST_FILE);
        ContextData data = contextParser.parse(file);
        System.out.println(data);

        assertEquals(MULTI_SUB_CONTEXT_COUNT, data.getSubContexts().size());
    }

    @Test
    void parse_recursiveSubContext() throws IOException {
        String file = ResourceReader.readFile(RECURSIVE_SUB_CONTEXT_TEST_FILE);
        ContextData data = contextParser.parse(file);

        ContextData innerContext = data;
        for (int i = 0; i < RECURSIVE_SUB_CONTEXT_NESTED_COUNT; i++) {
            // this should not throw exception, cuz sub context should exist
            innerContext = innerContext.getSubContexts().get(0);
            System.out.println("====================");
            System.out.println(innerContext.getSpelExp());
            System.out.println("====================");
        }
    }

}
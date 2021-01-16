package hr.kraljic.spel.textparser2.parser;

import hr.kraljic.spel.textparser2.parser.config.SpelConfig;
import hr.kraljic.spel.textparser2.resource.ResourceReader;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpelParserImplTest {
    private static final String SIMPLE_EXPRESSION_TEST_FILE = "/spel/test_simple_expression.txt";
    private static final String SIMPLE_EXPRESSION_OUT = "Hello Mark, how are you?";

    private static final String SIMPLE_SUB_EXPRESSION_TEST_FILE = "/spel/test_simple_sub_expression.txt";
    private static final String SIMPLE_SUB_EXPRESSION_OUT = "Hello Mark, how are you?\n" +
            "    You are from Ilica 1/b, Zagreb";

    private static final String MULTI_SUB_EXPRESSION_TEST_FILE = "/spel/test_multi_sub_expression.txt";
    private static final String MULTI_SUB_EXPRESSION_OUT = "Hello Mark, how are you?\n" +
            "    You are from Ilica 1/b, Zagreb, Croatia";

    private static final String SIMPLE_ARRAY_EXPRESSION_TEST_FILE = "/spel/test_simple_array_expression.txt";
    private static final String SIMPLE_ARRAY_EXPRESSION_OUT = "Hello Mark!\n" +
            "Hello Mark!\n" +
            "Hello Mark!";

    private static final String NESTED_ARRAY_EXPRESSION_TEST_FILE = "/spel/test_nested_array_expression.txt";
    private static final String NESTED_ARRAY_EXPRESSION_OUT = "Hello Mark, how are you?\n" +
            "    Your nickname is Mark\n" +
            "    Your nickname is Abc\n" +
            "    Your nickname is Test 123";

    private SpelConfig spelConfig = SpelConfig.builder().commentPrefix("#").filterEmptyLines(true).build();
    private SpelParser spelParser = new SpelParserImpl(spelConfig);

    @Test
    void parse_simpleExpression() throws IOException {
        Dummy dummy = createDummy();
        String text = ResourceReader.readFile(SIMPLE_EXPRESSION_TEST_FILE);
        String out = spelParser.parse(text, dummy);
        System.out.println(out);
        assertEquals(SIMPLE_EXPRESSION_OUT, out.trim());
    }

    @Test
    void parse_simpleSubExpression() throws IOException {
        Dummy dummy = createDummy();
        String text = ResourceReader.readFile(SIMPLE_SUB_EXPRESSION_TEST_FILE);
        String out = spelParser.parse(text, dummy);
        System.out.println(out);
        assertEquals(SIMPLE_SUB_EXPRESSION_OUT, out.trim());
    }

    @Test
    void parse_multiSubExpression() throws IOException {
        Dummy dummy = createDummy();
        String text = ResourceReader.readFile(MULTI_SUB_EXPRESSION_TEST_FILE);
        String out = spelParser.parse(text, dummy);
        System.out.println(out);
        assertEquals(MULTI_SUB_EXPRESSION_OUT, out.trim());
    }


    @Test
    void parse_simpleArrayExpression() throws IOException {
        Dummy[] dummy = new Dummy[] { createDummy(),  createDummy(),  createDummy()};
        String text = ResourceReader.readFile(SIMPLE_ARRAY_EXPRESSION_TEST_FILE);
        String out = spelParser.parse(text, dummy);
        System.out.println(out);
        assertEquals(SIMPLE_ARRAY_EXPRESSION_OUT, out.trim());
    }

    @Test
    void parse_nestedArrayExpression() throws IOException {
        Dummy dummy = createDummy();
        String text = ResourceReader.readFile(NESTED_ARRAY_EXPRESSION_TEST_FILE);
        String out = spelParser.parse(text, dummy);
        System.out.println(out);
        assertEquals(NESTED_ARRAY_EXPRESSION_OUT, out.trim());
    }


    private Dummy createDummy() {
        return new Dummy() {{
            setName("Mark");
            setAddress(new Address() {{
                setCity("Zagreb");
                setStreet("Ilica 1/b");
                setState(new State() {{
                    setStateName("Croatia");
                }});
            }});
            setNicknames(new Nickname[]{
                    new Nickname("Mark"),
                    new Nickname("Abc"),
                    new Nickname("Test 123"),
            });
        }};
    }

    @Data
    static class Dummy {
        String name;
        Address address;
        Nickname[] nicknames;

        @Data
        static class Address {
            String city;
            String street;
            State state;

            @Data
            static class State {
                String stateName;
            }
        }

        @Data
        @AllArgsConstructor
        static class Nickname {
            String nickname;
        }
    }
}
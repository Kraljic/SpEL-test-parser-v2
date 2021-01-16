package hr.kraljic.spel.textparser2.parser;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ContextExpressionParserImplTest {
    private static final String EXPRESSION_TEST_1 = "${variable}";
    private static final String EXPRESSION_TEST_1_OUT = "{${variable}=variable}";

    private static final String EXPRESSION_TEST_2 = "Hello ${variable} wordl!";
    private static final String EXPRESSION_TEST_2_OUT = "{${variable}=variable}";

    private static final String EXPRESSION_TEST_3 = "Hello ${variable}${test} ''this'' wordl!";
    private static final String EXPRESSION_TEST_3_OUT = "{${test}=test, ${variable}=variable}";

    ContextExpressionParser contextExpressionParser = new ContextExpressionParserImpl();

    @Test
    void parse_1() {
        Map<String, String> parsed = contextExpressionParser.parse(EXPRESSION_TEST_1);
        System.out.println(parsed);
        assertEquals(EXPRESSION_TEST_1_OUT, parsed.toString());
    }

    @Test
    void parse_2() {
        Map<String, String> parsed = contextExpressionParser.parse(EXPRESSION_TEST_2);
        System.out.println(parsed);
        assertEquals(EXPRESSION_TEST_2_OUT, parsed.toString());
    }

    @Test
    void parse_3() {
        Map<String, String> parsed = contextExpressionParser.parse(EXPRESSION_TEST_3);
        System.out.println(parsed);
        assertEquals(EXPRESSION_TEST_3_OUT, parsed.toString());
    }
}
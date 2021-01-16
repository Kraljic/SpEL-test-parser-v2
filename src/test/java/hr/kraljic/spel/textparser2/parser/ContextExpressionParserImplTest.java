package hr.kraljic.spel.textparser2.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContextExpressionParserImplTest {
    private static final String EXPRESSION_TEST_1 = "${variable}";
    private static final String EXPRESSION_TEST_1_OUT = "'' + variable + ''";

    private static final String EXPRESSION_TEST_2 = "Hello ${variable} wordl!";
    private static final String EXPRESSION_TEST_2_OUT = "'Hello ' + variable + ' wordl!'";

    private static final String EXPRESSION_TEST_3 = "Hello ${variable}${test} ''this'' wordl!";
    private static final String EXPRESSION_TEST_3_OUT = "'Hello ' + variable + '' + test + ' ''this'' wordl!'";

    ContextExpressionParser contextExpressionParser = new ContextExpressionParserImpl();

    @Test
    void parse_1() {
        String parsed = contextExpressionParser.parse(EXPRESSION_TEST_1);
        System.out.println(parsed);
        assertEquals(EXPRESSION_TEST_1_OUT, parsed);
    }

    @Test
    void parse_2() {
        String parsed = contextExpressionParser.parse(EXPRESSION_TEST_2);
        System.out.println(parsed);
        assertEquals(EXPRESSION_TEST_2_OUT, parsed);
    }

    @Test
    void parse_3() {
        String parsed = contextExpressionParser.parse(EXPRESSION_TEST_3);
        System.out.println(parsed);
        assertEquals(EXPRESSION_TEST_3_OUT, parsed);
    }
}
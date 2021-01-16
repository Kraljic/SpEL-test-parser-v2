package hr.kraljic.spel.textparser2.parser;

import java.util.Map;

interface ContextExpressionParser {
    Map<String, String> parse(String expression);
}

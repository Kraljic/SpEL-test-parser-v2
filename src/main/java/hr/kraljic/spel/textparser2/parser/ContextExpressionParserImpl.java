package hr.kraljic.spel.textparser2.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ContextExpressionParserImpl implements ContextExpressionParser {
    private static final String SPEL_REGEX = "\\$\\{(?<expression>.+?)}";
    private static final Pattern SPEL_PATTERN = Pattern.compile(SPEL_REGEX, Pattern.MULTILINE);

    @Override
    public String parse(String text) {
        text = "'" + text + "'";

        final Matcher matcher = SPEL_PATTERN.matcher(text);

        while (matcher.find()) {
            String match = matcher.group(0);
            String expression = matcher.group("expression");

            expression = maskAsExpression(expression);

            text = text.replace(match, expression);
        }

        return text;
    }

    private static String maskAsExpression(String text) {
        return "' + " + text + " + '";
    }
}

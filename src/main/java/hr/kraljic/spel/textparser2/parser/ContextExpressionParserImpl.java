package hr.kraljic.spel.textparser2.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ContextExpressionParserImpl implements ContextExpressionParser {
    private static final String SPEL_REGEX = "\\$\\{(?<expression>.+?)}";
    private static final Pattern SPEL_PATTERN = Pattern.compile(SPEL_REGEX, Pattern.MULTILINE);

    @Override
    public Map<String, String> parse(String text) {
        final Matcher matcher = SPEL_PATTERN.matcher(text);

        Map<String, String> tokens = new HashMap<>();
        while (matcher.find()) {
            String match = matcher.group(0);
            String expression = matcher.group("expression");

            tokens.put(match, expression);
        }

        return tokens;
    }

}

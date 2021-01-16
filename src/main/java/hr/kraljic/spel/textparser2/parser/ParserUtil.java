package hr.kraljic.spel.textparser2.parser;

import hr.kraljic.spel.textparser2.parser.config.SpelConfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ParserUtil {
    private static final String COMMENT_REGEX = "^{{comment_prefix}}.*?$(\\r?\\n)?";
    private static final String NEW_LINE_REGEX = "^\\s*$\\r?\\n";

    public static String filterCommentLines(String text, SpelConfig spelConfig) {
        String commentPrefix = spelConfig.getCommentPrefix();

        if (spelConfig.getCommentPrefix() == null) {
            return text;
        }

        commentPrefix = commentPrefix.trim();
        if (commentPrefix.isEmpty()) {
            return text;
        }

        final String regex = COMMENT_REGEX.replace("{{comment_prefix}}", commentPrefix);
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE | Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(text);
        return matcher.replaceAll("");
    }

    public static String filterEmptyLines(String text, SpelConfig spelConfig) {
        boolean isFilterEmptyLines = spelConfig.isFilterEmptyLines();
        if (!isFilterEmptyLines) {
            return text;
        }

        final Pattern pattern = Pattern.compile(NEW_LINE_REGEX, Pattern.MULTILINE | Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(text);
        return matcher.replaceAll("");
    }
}

package hr.kraljic.spel.textparser2.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ContextParserImpl implements ContextParser {
    private static final String CONTEXT_REGEX = "%%context(?<index>[0-9]*?)?::(?<contextObj>.+?)%%\\n(?<spelExp>.*?)\\n%%context\\k<index>_end::\\k<contextObj>%%";
    private static final Pattern CONTEXT_PATTERN = Pattern.compile(CONTEXT_REGEX, Pattern.MULTILINE | Pattern.DOTALL);

    @Override
    public ContextData parse(String text) {
        ContextData contextContainers = new ContextData(text);

        List<ContextData> subContextList = parseSubContext(text);
        contextContainers.setSubContexts(subContextList);
        contextContainers.setSpelExp(text);

        return contextContainers;
    }

    private List<ContextData> parseSubContext(String text) {
        final Matcher matcher = CONTEXT_PATTERN.matcher(text);

        List<ContextData> subContextList = new ArrayList<>();
        while (matcher.find()) {
            String subContextRaw = matcher.group(0);
            String subContextExp = matcher.group("spelExp");
            String subContextObj = matcher.group("contextObj");

            ContextData subContext = new ContextData(subContextRaw);
            List<ContextData> innerSubContext = parseSubContext(subContextExp);

            subContext.setSpelExp(subContextExp);
            subContext.setContextObj(subContextObj);
            subContext.setSubContexts(innerSubContext);

            subContextList.add(subContext);
        }

        return subContextList;
    }
}

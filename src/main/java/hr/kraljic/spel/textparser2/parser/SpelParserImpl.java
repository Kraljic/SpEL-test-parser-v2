package hr.kraljic.spel.textparser2.parser;

import hr.kraljic.spel.textparser2.parser.config.ExceptionHandler;
import hr.kraljic.spel.textparser2.parser.config.SpelConfig;
import hr.kraljic.spel.textparser2.resource.ResourceReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Slf4j
public class SpelParserImpl implements SpelParser {
    private final SpelConfig spelConfig;

    private ExpressionParser expressionParser = new SpelExpressionParser();
    private StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
    private ContextParser contextParser = new ContextParserImpl();
    private ContextExpressionParser contextExpressionParser = new ContextExpressionParserImpl();


    public SpelParserImpl(SpelConfig spelConfig) {
        this.spelConfig = spelConfig;
    }

    @Override
    public String parse(String text, Object context) {
        return parseText(text, context);
    }

    @Override
    public String parse(File textFile, Object context) throws IOException {
        String text = ResourceReader.readFile(textFile.getPath());
        return parseText(text, context);
    }

    private String parseText(String text, Object context) {
        text = ParserUtil.filterCommentLines(text, spelConfig);

        ContextData contextData = contextParser.parse(text);
        text = parse(contextData, context);

        text = ParserUtil.filterEmptyLines(text, spelConfig);

        return text;
    }

    private String parse(ContextData data, Object context) {
        parseSubContext(data, context);

        if (context.getClass().isArray()) {
            context = Arrays.asList((Object[]) context);
        }

        if (Iterable.class.isAssignableFrom(context.getClass())) {
            parseArrayContext(data, context);
        } else {
            parseContext(data, context);
        }

        return data.getSpelExp();
    }

    public void parseSubContext(ContextData data, Object context) {
        String out = data.getSpelExp();

        for (ContextData subContext : data.getSubContexts()) {
            Object subContextObj = getContextObject(subContext, context);

            String parsedSubContext = parse(subContext, subContextObj);
            out = out.replace(subContext.getRaw(), parsedSubContext);
        }

        data.setSpelExp(out);
    }

    public void parseArrayContext(ContextData data, Object context) {
        String out = data.getSpelExp();

        StringBuilder contextArray = new StringBuilder();
        for (Object obj : (Iterable<?>) context) {
            ContextData dataCopy = new ContextData(data);

            String arrayItem = parse(dataCopy, obj);
            contextArray.append(arrayItem);
        }
        out = out.replace(data.getSpelExp(), contextArray.toString());

        data.setSpelExp(out);
    }

    public void parseContext(ContextData data, Object context) {
        String out = data.getSpelExp();

        Map<String, String> tokens = contextExpressionParser.parse(out);
        for (Map.Entry<String, String> tokenEntry : tokens.entrySet()) {
            String token = tokenEntry.getKey();
            String exp = tokenEntry.getValue();

            String value = fetchValue(token, exp, context);

            out = out.replace(token, value);
        }

        data.setSpelExp(out);
    }

    private Object getContextObject(ContextData contextData, Object context) {
        String contextObjExpression = contextData.getContextObj();
        Expression expression = expressionParser.parseExpression(contextObjExpression);

        return expression.getValue(evaluationContext, context);
    }

    private String fetchValue(String token, String exp, Object context) {
        Expression expression = expressionParser.parseExpression(exp);
        try {
            String value = expression.getValue(evaluationContext, context, String.class);

            if (value != null) {
                return value;
            } else  {
                return spelConfig.getNullHandler().getDefaultValue();
            }
        } catch (EvaluationException ex) {
            log.warn(ex.getMessage());

            return handleException(token, ex);
        }
    }

    private String handleException(String token, EvaluationException ex) {
        switch (spelConfig.getExceptionHandler()) {
            case THROW_EXCEPTION:
                throw new SpelParserException(ex);
            case PRINT_EMPTY_STRING:
                return "";
            case PRINT_WHITESPACE:
                return " ";
            case RETAIN_EXPRESSION:
            default:
                return token;
        }
    }
}

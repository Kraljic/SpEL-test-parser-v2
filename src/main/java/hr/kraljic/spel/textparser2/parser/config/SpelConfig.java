package hr.kraljic.spel.textparser2.parser.config;

import lombok.*;

import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SpelConfig {
    private boolean filterEmptyLines;
    private String commentPrefix;
    private NullHandler nullHandler;
    private ExceptionHandler exceptionHandler;

    @Singular
    private Map<Class<?>, TypeResolver<?>> typeResolvers;

    public NullHandler getNullHandler() {
        if (nullHandler == null) {
            return NullHandler.DEFAULT;
        }
        return nullHandler;
    }

    public ExceptionHandler getExceptionHandler() {
        if (exceptionHandler == null) {
            return ExceptionHandler.DEFAULT;
        }
        return exceptionHandler;
    }
}

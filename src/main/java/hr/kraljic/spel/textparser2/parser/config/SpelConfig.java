package hr.kraljic.spel.textparser2.parser.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SpelConfig {
    private boolean filterEmptyLines;
    private String commentPrefix;
    private NullHandler nullHandler;
    private ExceptionHandler exceptionHandler;

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

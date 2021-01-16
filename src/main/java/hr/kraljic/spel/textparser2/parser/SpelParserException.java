package hr.kraljic.spel.textparser2.parser;

public class SpelParserException extends RuntimeException {
    public SpelParserException() {
        super();
    }

    public SpelParserException(String message) {
        super(message);
    }

    public SpelParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpelParserException(Throwable cause) {
        super(cause);
    }

    public SpelParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

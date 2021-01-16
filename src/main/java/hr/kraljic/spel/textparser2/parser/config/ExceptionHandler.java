package hr.kraljic.spel.textparser2.parser.config;

public enum ExceptionHandler {
    THROW_EXCEPTION,
    RETAIN_EXPRESSION,
    PRINT_WHITESPACE,
    PRINT_EMPTY_STRING;

    public static final ExceptionHandler DEFAULT = ExceptionHandler.RETAIN_EXPRESSION;
}

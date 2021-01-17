package hr.kraljic.spel.textparser2.parser.config;

/**
 * Defines how will SpEL expression be handled in case of exception.
 */
public enum ExceptionHandler {
    /**
     * The exception will be rethrown and the process will terminate.
     */
    THROW_EXCEPTION,
    /**
     * Expression will be retained, if expression is in sub context every parent context will try to parse expression.
     * <p>Use this handler if u want to share parent context in sub context
     */
    RETAIN_EXPRESSION,
    /**
     * Expression will be replaced with single whitespace.
     */
    PRINT_WHITESPACE,
    /**
     * Expression will be ignored (removed from text).
     */
    PRINT_EMPTY_STRING;

    public static final ExceptionHandler DEFAULT = ExceptionHandler.RETAIN_EXPRESSION;
}

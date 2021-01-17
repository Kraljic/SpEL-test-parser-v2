package hr.kraljic.spel.textparser2.parser.config;

public enum NullHandler {
    /**
     * Nulls will be replace with single white space
     */
    PRINT_WHITE_SPACE(" "),
    /**
     * Nulls wont be printed at all
     */
    PRINT_EMPTY_STRING(""),
    /**
     * Nulls will be printed as "null"
     */
    PRINT_NULL("null"),
    ;
    public static final NullHandler DEFAULT = PRINT_NULL;

    private final String defaultValue;

    NullHandler(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}

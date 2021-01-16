package hr.kraljic.spel.textparser2.parser.config;

public enum NullHandler {
    PRINT_WHITE_SPACE(" "),
    PRINT_EMPTY_STRING(""),
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

package hr.kraljic.spel.textparser2;

import hr.kraljic.spel.textparser2.parser.SpelParser;
import hr.kraljic.spel.textparser2.parser.SpelParserImpl;
import hr.kraljic.spel.textparser2.parser.config.ExceptionHandler;
import hr.kraljic.spel.textparser2.parser.config.NullHandler;
import hr.kraljic.spel.textparser2.parser.config.SpelConfig;
import hr.kraljic.spel.textparser2.parser.custom.DateTimeTypeResolver;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SpelParserFactory {
    public static SpelParser create() {
        SpelConfig spelConfig =
                SpelConfig.builder()
                        .filterEmptyLines(true)
                        .commentPrefix("#")
                        .exceptionHandler(ExceptionHandler.RETAIN_EXPRESSION)
                        .nullHandler(NullHandler.PRINT_NULL)
                        .typeResolver(LocalDate.class, new DateTimeTypeResolver<LocalDate>("dd.MM.yyyy"))
                        .typeResolver(LocalDateTime.class, new DateTimeTypeResolver<LocalDateTime>("dd.MM.yyyy HH:mm:ss"))
                        .build();

        return new SpelParserImpl(spelConfig);
    }
}

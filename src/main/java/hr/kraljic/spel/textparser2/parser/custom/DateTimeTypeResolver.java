package hr.kraljic.spel.textparser2.parser.custom;

import hr.kraljic.spel.textparser2.parser.config.TypeResolver;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class DateTimeTypeResolver<T extends TemporalAccessor> implements TypeResolver<TemporalAccessor> {
    private DateTimeFormatter dateTimeFormatter;

    public DateTimeTypeResolver(String pattern) {
        dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public String resolve(TemporalAccessor temporalAccessor) {
        return dateTimeFormatter.format(temporalAccessor);
    }
}

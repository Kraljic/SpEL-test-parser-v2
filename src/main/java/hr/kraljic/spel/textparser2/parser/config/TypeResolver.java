package hr.kraljic.spel.textparser2.parser.config;

public interface TypeResolver<T> {
    String resolve(T data);
}

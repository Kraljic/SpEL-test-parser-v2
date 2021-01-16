package hr.kraljic.spel.textparser2.parser;

import java.io.File;
import java.io.IOException;

public interface SpelParser {
    String parse(String text, Object context);

    String parse(File textFile, Object context) throws IOException;
}

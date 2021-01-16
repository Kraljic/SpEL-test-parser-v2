package hr.kraljic.spel.textparser2.resource;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ResourceReader {
    public static String readFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceReader.class.getResourceAsStream(fileName)))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }
}
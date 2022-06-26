package io;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class PatternsReader implements IReader {
    private Scanner scanner;

    @Override
    public List<Pattern> readPatterns() {
        List<Pattern> patterns = new ArrayList<>();
        while (scanner.hasNext()) {
            patterns.add(stringToPattern(scanner.nextLine()));
        }
        return patterns;
    }

    private Pattern stringToPattern(String src) {
        String[] field = src
                .replace("\"", "")
                .replace("\r", "")
                .replace("\n", "")
                .split(";");

        return new Pattern(field[0], field[1], field[2]);
    }
}

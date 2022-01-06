package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        String[] out = toString().split(System.lineSeparator());
        for (String line : out) {
            line = line.strip();
            if (!line.startsWith("#") && line.contains("=")) {
                if (line.startsWith("=")) {
                    throw new IllegalArgumentException();
                }
                String[] pair = line.split("=");
                if (line.endsWith("=")) {
                    values.put(pair[0], null);
                    continue;
                }
                values.put(pair[0], pair[1]);
            }
        }
    }

    public String value(String key) {
        if (!values.containsKey(key)) {
            throw new NoSuchElementException();
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
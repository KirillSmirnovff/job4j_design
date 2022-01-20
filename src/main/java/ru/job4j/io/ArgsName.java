package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Key doesn't exist");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            if (!arg.startsWith("-") || !arg.contains("=")) {
                throw new IllegalArgumentException("Wrong argument format");
            }
            String[] pair = arg.replaceFirst("-", "").split("=", 2);
            if (Objects.equals(pair[0], "") || Objects.equals(pair[1], "")) {
                throw new IllegalArgumentException("Key or value is null");
            }
            values.put(pair[0], pair[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        StringJoiner joiner = new StringJoiner(",");
        List<String> result = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path"))).
                useDelimiter(argsName.get("delimiter"))) {
            while (scanner.hasNext()) {
                joiner.add(scanner.next());
            }
        }
        System.out.println(joiner);
    }

    private ArgsName validation(String[] args) {
        if (args.length != 4) {
            throw  new IllegalArgumentException("Need to set 4 arguments: root path, delimiter, out path and filter");
        }
        ArgsName argsName = ArgsName.of(args);
        String rootPath = argsName.get("path");
        String outPath = argsName.get("out");
        argsName.get("filter");
        argsName.get("delimiter");
        if (!Path.of(rootPath).toFile().exists()) {
            throw new IllegalArgumentException("Root file doesn't exist");
        }
        if (!("stdout".equals(outPath) || outPath.endsWith(".csv"))) {
            throw new IllegalArgumentException("Wrong output format");
        }
        return argsName;
    }
}
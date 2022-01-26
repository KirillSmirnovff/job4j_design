package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Test {
    public static void main(String[] args) throws Exception {
        StringJoiner joiner = new StringJoiner(",");
        List<String> result = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream("Test.csv")).
                useDelimiter(";")) {
            while (scanner.hasNext()) {
                joiner.add(scanner.next());
            }
        }
        System.out.println(joiner);
    }
}
package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder and search condition is null");
        }
        if (args[0] == null) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar search.jar ROOT_FOLDER.");
        }
        if (args.length < 2 || args[1] == null) {
            throw new IllegalArgumentException("Search condition is null");
        }
        Search searcher = new Search();
        Path start = Paths.get(args[0]);
        searcher.search(start, p -> p.toFile().getName().startsWith(args[1])).forEach(System.out::println);
    }

    public List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
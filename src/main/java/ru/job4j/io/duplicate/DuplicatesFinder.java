package ru.job4j.io.duplicate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesFinder finder = new DuplicatesFinder();
        finder.duplicateFind(Path.of("./")).forEach(System.out::println);

    }

    public List<Path> duplicateFind(Path root) throws IOException {
        DuplicatesVisitor searcher = new DuplicatesVisitor();
        List<Path> result = new ArrayList<>();
        Files.walkFileTree(root, searcher);
        Map<FileProperty, List<Path>> files = searcher.getFiles();
        for (List<Path> fileList : files.values()) {
            if (fileList.size() > 1) {
                result.addAll(fileList);
            }
        }
        return result;
    }
}
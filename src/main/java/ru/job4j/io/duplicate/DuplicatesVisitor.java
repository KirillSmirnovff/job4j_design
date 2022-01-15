package ru.job4j.io.duplicate;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> files = new HashMap<>();

    public Map<FileProperty, List<Path>> getFiles() {
        return files;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (!files.containsKey(fileProperty)) {
            files.put(fileProperty, new ArrayList<>(List.of(file.toAbsolutePath())));
        } else {
            files.get(fileProperty).add(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}
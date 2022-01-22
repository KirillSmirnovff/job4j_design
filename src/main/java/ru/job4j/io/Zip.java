package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(Path source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String[] validation(ArgsName args) {
        String[] values = {
                args.get("d"),
                args.get("e").replace("*", ""),
                args.get("o")
        };
        Path root = Paths.get(values[0]);
        if (!root.toFile().exists()) {
            throw new IllegalArgumentException("Root folder doesn't exist");
        }
        return values;
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        String[] values = validation(argsName);
        Search search = new Search();
        List<Path> sources = search.search(Paths.get(values[0]), p -> !p.toFile().getName().endsWith(values[1]));
        packFiles(sources, Path.of(values[2]));
    }
}
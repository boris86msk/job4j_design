package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        paramValidate(args);
        Path start = Paths.get(args[0]);
        Predicate<Path> pred = p -> p.toFile().getName().endsWith(args[1]);
        List<Path> list = search(start, pred);
        list.forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    static void paramValidate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java " +
                    "-jar dir.jar ROOT_FOLDER.");
        }
        if (!args[1].startsWith(".") && ) {

        }
    }

}

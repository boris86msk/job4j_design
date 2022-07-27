package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> map = new HashMap<>();

    public void printToDuplicates() {
        for (FileProperty f : map.keySet()) {
            if (map.get(f).size() > 1) {
                System.out.println("file name: " + "\"" + f.getName() + "\""
                        + " size in bytes: " + f.getSize()
                        + " paths: " + map.get(f));
            }
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        if (!map.containsKey(fileProperty)) {
            map.put(fileProperty, new ArrayList<>());
            map.get(fileProperty).add(file.toAbsolutePath());
        } else {
            map.get(fileProperty).add(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}

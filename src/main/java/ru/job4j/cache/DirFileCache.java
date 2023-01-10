package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) throws IOException {
        return Files.readString(Path.of(cachingDir, key));
    }

    public void getListFiles() throws IOException {
        System.out.println("Найденые файлы:");
        Files.walkFileTree(Paths.get(cachingDir), new PrintFiles());
    }
}
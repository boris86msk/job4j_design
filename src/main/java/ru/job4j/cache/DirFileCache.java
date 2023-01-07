package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) throws IOException {
        String rsl = "";
        if (!cache.containsKey(key)) {
            String text = Files.readString(Paths.get(String.format("%s\\%s", cachingDir, key)));
            put(key, text);
            System.out.println("<<<Загружалось в кэш>>>");
            rsl = get(key);
        } else {
            System.out.println("<<<Выгружено из кэш>>>");
            rsl = get(key);
        }
        return rsl;
    }

    public void getListFiles() throws IOException {
        System.out.println("Найденые файлы:");
        Files.walkFileTree(Paths.get(cachingDir), new PrintFiles());
    }
}

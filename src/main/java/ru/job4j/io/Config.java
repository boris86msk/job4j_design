package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            values = read.lines()
                    .filter(s -> !s.isEmpty() && !s.startsWith("#"))
                    .filter(e -> {
                        if (!e.contains("=")) {
                            throw new IllegalArgumentException(
                                    "the string does not contain the equal sign");
                        }
                        return true;
                    })
                    .map(e -> e.split("=", 2))
                    .filter(e -> {
                        if (e[0].isEmpty() || e[1].isEmpty()) {
                            throw new IllegalArgumentException(
                                    "there is no value before or after the equal sign"
                            );
                        }
                        return true;
                    })
                    .collect(Collectors.toMap(e -> e[0], e -> e[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    /**
     * переопределение toString нужно для считывания файла по path
     * именно в toString создаются объекты класса  BufferedReader и FileReader
     * и идет чтение файла по указанной ссылке.
     *
     * @return
     */
    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

}
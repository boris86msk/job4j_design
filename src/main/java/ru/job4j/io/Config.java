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
    private Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        Map<String, String> val = null;
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            val = read.lines()
                    .filter(s -> !s.startsWith("#") && s.contains("="))
                    .map(e -> e.split("=", 2))
                    .filter(e -> !e[0].isEmpty() && !e[1].isEmpty())
                    .collect(Collectors.toMap(e -> e[0], e -> e[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        values = val;
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
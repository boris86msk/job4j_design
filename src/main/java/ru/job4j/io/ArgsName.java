package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(
                    "no data available");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "no data available");
        }
        values.putAll(Arrays.stream(args)
                .filter(e -> {
                    if (!e.startsWith("-")) {
                        throw new IllegalArgumentException(
                                "the string does not start with the character \"-\"");
                    }
                    return true;
                })
                .map(e -> e.substring(1))
                .filter(e -> {
                    if (!e.contains("=")) {
                        throw new IllegalArgumentException(
                                "the equal sign is missing");
                    }
                    return true;
                })
                .map(e -> e.split("=", 2))
                .filter(e -> {
                    if (e[0].isEmpty() || e[1].isEmpty()) {
                        throw new IllegalArgumentException(
                                "there is no key or value in the parameter");
                    }
                    return true;
                })
                .collect(Collectors.toMap(e -> e[0], e -> e[1])));
    }


    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}

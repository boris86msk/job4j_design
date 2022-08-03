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
        values.putAll(Arrays.stream(args)
                .filter(e -> {
                    argsValid(e);
                    return true;
                })
                .map(e -> e.substring(1))
                .map(e -> e.split("=", 2))
                .collect(Collectors.toMap(e -> e[0], e -> e[1])));
    }


    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "no data available");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private void argsValid(String arg) {
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException(
                        "the string does not start with the character \"-\"");
            }
            if (!arg.contains("=")) {
                throw new IllegalArgumentException(
                        "the equal sign is missing");
            }

            arg = arg.substring(1);
            String[] a = arg.split("=", 2);
            if (a[0].isEmpty() || a[1].isEmpty()) {
                throw new IllegalArgumentException(
                        "there is no key or value in the parameter");
            }

    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}

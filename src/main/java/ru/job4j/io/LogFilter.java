package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    static List<String> filter(String file) {
        List<String> rst = null;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            rst = in.lines()
                    .filter(line -> line.contains(" 404 "))
                    .collect(Collectors.toList());
        } catch (Exception IOException) {
            IOException.printStackTrace();
        }
        return rst;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String line : log) {
                out.printf("%s%n", line);
            }
        } catch (Exception IOException) {
            IOException.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");

    }
}

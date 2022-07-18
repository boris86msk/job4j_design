package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> list;
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)))) {
            list = in.lines()
                    .filter(e -> !e.isEmpty())
                    .toList();

            boolean server = true;
            String timeStart = "";
            for (String line : list) {
                if (server && (line.startsWith("400") || line.startsWith("500"))) {
                    timeStart = line.split(" ")[1];
                    server = false;
                } else if (!server && (line.startsWith("200") || line.startsWith("300"))) {
                    out.println(timeStart + ";" + line.split(" ")[1] + ";");
                    server = true;
                }
            }
        } catch (Exception IOException) {
            IOException.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("data/server.log", "data/server.target");
    }
}

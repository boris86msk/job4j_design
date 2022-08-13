package ru.job4j.io;

import java.io.*;
public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)))) {
            boolean server = true;
            String timeStart = "";
            String read;
            while ((read = in.readLine()) != null) {
                if (server && (read.startsWith("400") || read.startsWith("500"))) {
                    timeStart = read.split(" ")[1];
                    server = false;
                } else if (!server && (read.startsWith("200") || read.startsWith("300"))) {
                    out.println(timeStart + ";" + read.split(" ")[1] + ";");
                    server = true;
                }
            }
        } catch (Exception ioexception) {
            ioexception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("data/server.log", "data/server.target");
    }
}

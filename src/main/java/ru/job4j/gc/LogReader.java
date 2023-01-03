package ru.job4j.gc;

import java.io.FileReader;

public class LogReader {
    public static void main(String[] args) {

        try (FileReader rid = new FileReader("logSort.txt..4")) {
            StringBuilder text = new StringBuilder();
            int rd;
            while ((rd = rid.read()) != -1) {
                text.append((char) rd);
            }
            String[] st = text.toString().split("\n");
            for (String s : st) {
                String[] p = s.split("\s+", 5);
                if (p[4].startsWith("ru")) {
                    System.out.println(p[1] + " " + p[2] + " " + p[3] + " " + p[4]);
                }
            }
            System.out.println("-------------------------------------------------");
            for (String s : st) {
                String[] p = s.split("\s+", 5);
                if (Integer.parseInt(p[3]) > 500000) {
                    System.out.println(p[1] + " " + p[2] + " " + p[3] + " " + p[4]);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
        // 420: 4 64 ru.job4j.gc.leak.Generate$$Lambda$17/0x0000000800c039f0
        // 582: 1 24 ru.job4j.gc.leak.CommentGenerator
        // 714: 1 16 ru.job4j.gc.leak.PostStore
        // 715: 1 16 ru.job4j.gc.leak.UserGenerator
        // -------------------------------------------------
        // 1: 14949 1451040 [B (java.base@18.0.1)

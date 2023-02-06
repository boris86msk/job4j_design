package ru.job4j.ood.tdd.srp;


import java.io.FileReader;

public class ExampleSRP {

    public void printText(String path) {
        try (FileReader rid = new FileReader(path)) {
            StringBuilder text = new StringBuilder();
            int rd;
            while ((rd = rid.read()) != -1) {
                text.append((char) rd);
            }
            System.out.println("---------beginning---------");
            System.out.println(text);
            System.out.println("-----------end------------");
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

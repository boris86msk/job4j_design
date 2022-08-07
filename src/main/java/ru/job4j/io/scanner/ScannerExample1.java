package ru.job4j.io.scanner;

import java.io.CharArrayReader;
import java.util.Scanner;

public class ScannerExample1 {
    public static void main(String[] args) {
//        var ls = System.lineSeparator();
//        var data = String.join(ls,
//                "1 2 3",
//                "4 5 6",
//                "7 8 9"
//        );
//        var scanner = new Scanner(new CharArrayReader(data.toCharArray()));
//        while (scanner.hasNextInt()) {
//            System.out.print(scanner.nextInt());
//            System.out.print(" ");
//        }

        String data = String.join(
                ";",
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        System.out.println(data);
    }
}

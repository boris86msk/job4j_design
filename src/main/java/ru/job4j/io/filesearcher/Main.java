package ru.job4j.io.filesearcher;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
Searcher.main(new String[]{"-d=E:\\ДОКУМЕНТЫ\\Боря", "-n=*.pdf", "-t=mask", "-o=log34.txt"});
    }
}

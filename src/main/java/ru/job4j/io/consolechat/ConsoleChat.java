package ru.job4j.io.consolechat;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final List<String> botAnswers;

    public ConsoleChat(String path, String answersPath) {
        this.path = path;
        this.botAnswers = readPhrases(answersPath);
    }

    public void run() {
        List<String> chatList = new ArrayList<>();
        boolean end = true;
        boolean answers = true;
        Scanner in = new Scanner(System.in);
        System.out.println("Привет! это чат бот, \nесли вы хотите отключить ответы, введите: "
                + STOP + "\n" + "eсли хотите возобновить ответы бота, введиет: "
                + CONTINUE + "\n" + "для выхода из чата введите: "
                + OUT + "\n" + "ChatBot ожидает диалог:");
        while (end) {
            String str = in.nextLine();
            chatList.add("User: " + str);
            if (str.contains(OUT)) {
                end = false;
                saveLog(chatList);
                continue;
            } else if (str.contains(STOP)) {
                answers = false;
            } else if (str.contains(CONTINUE)) {
                answers = true;
            }
            if (answers) {
                String answerBot = botAnswers.get((int) (Math.random() * (botAnswers.size() - 1)));
                System.out.println(answerBot);
                chatList.add("\t\t\t\t Bot: " + answerBot);
            }
        }
    }

    private List<String> readPhrases(String answersPath) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(answersPath))) {
            list = in.lines().toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        Date date = new Date();
        SimpleDateFormat dateForm = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
        try (PrintWriter pw = new PrintWriter(new FileWriter(
                path, Charset.forName("WINDOWS-1251"), true))) {
            pw.println("чат создан " + dateForm.format(date));
            for (String s : log) {
                pw.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("src/data/consoleChatLog.txt", "src/main/java/ru/job4j/io/consoleChat/answers1.txt");
        cc.run();
    }
}

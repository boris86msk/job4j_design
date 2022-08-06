package ru.job4j.io.consoleChat;

import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("Привет! это чат бот, \nесли вы хотите отключить ответы, введите: " + STOP + "\n"
        + "eсли хотите возобновить ответы бота, введиет: " + CONTINUE + "\n"
                + "для выхода из чата введите: " + OUT);
        System.out.print("ChatBot ожидает диалог:");
        String str = in.nextLine();
        Chat chat = new Chat(this.path);
        System.out.println(chat);
    }

    private List<String> readPhrases() {
        return null;
    }

    private void saveLog(List<String> log) {
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("src/data/consoleChatLog.txt", "answers1.txt");
        cc.run();
    }
}

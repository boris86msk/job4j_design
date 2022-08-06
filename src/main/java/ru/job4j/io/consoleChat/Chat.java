package ru.job4j.io.consoleChat;

import java.util.Date;

public class Chat {
    private  final Date date;
    private final String path;

    public Chat(String path) {
        this.date = new Date();
        this.path = path;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "date=" + date +
                ", path='" + path + '\'' +
                '}';
    }
}

package ru.job4j.gc.leak;

import java.util.Random;
import java.util.Scanner;

public class Menu {
    public static int addPost = 1;
    public static int addManyPost = 2;
    public static int showAllPosts = 3;
    public static int deletePost = 4;

    public static String select = "Выберите меню";
    public static String count = "Выберите количество создаваемых постов";
    public static String textOfPost = "Введите текст";
    public static String exit = "Конец работы";

    public static String menu = """
                Введите 1 для создание поста.
                Введите 2, чтобы создать определенное количество постов.
                Введите 3, чтобы показать все посты.
                Введите 4, чтобы удалить все посты.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        Random random = new Random();
        UserGenerator userGenerator = new UserGenerator(random);
        CommentGenerator commentGenerator = new CommentGenerator(random, userGenerator);
        PostStore postStore = new PostStore();
        Scanner scanner = new Scanner(System.in);
        start(commentGenerator, scanner, userGenerator, postStore);
    }

    private static void start(CommentGenerator commentGenerator, Scanner scanner, UserGenerator userGenerator, PostStore postStore) {
        boolean run = true;
        while (run) {
            System.out.println(menu);
            System.out.println(select);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (addPost == userChoice) {
                System.out.println(textOfPost);
                String text = scanner.nextLine();
                userGenerator.generate();
                commentGenerator.generate();
                postStore.add(new Post(text, commentGenerator.getComments()));
            } else if (addManyPost == userChoice) {
                System.out.println(textOfPost);
                String text = scanner.nextLine();
                System.out.println(count);
                String count = scanner.nextLine();
                for (int i = 0; i < Integer.parseInt(count); i++) {
                    createPost(commentGenerator, userGenerator, postStore, text);
                }
            } else if (showAllPosts == userChoice) {
                System.out.println(postStore.getPosts());
            } else if (deletePost == userChoice) {
                System.out.println("deleted " + postStore.getRemove() + " posts");
                postStore.removeAll();
            } else {
                run = false;
                System.out.println(exit);
            }
        }
    }

    private static void createPost(CommentGenerator commentGenerator,
                                   UserGenerator userGenerator, PostStore postStore, String text) {
        userGenerator.generate();
        commentGenerator.generate();
        postStore.add(new Post(text, commentGenerator.getComments()));
    }
}
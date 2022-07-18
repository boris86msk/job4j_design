package ru.job4j.question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rst = null;
        if (previous.size() == current.size()
                && previous.containsAll(current)) {
            rst = new Info(0, 0, 0);
        } else {
            previous.retainAll(current);
            int size = current.size();
            rst = new Info(0, 0, size);
        }
        return rst;
    }

    public static void main(String[] args) {
        Set<User> set = new HashSet<>();
        set.add(new User(1, "A"));
        set.add(new User(2, "B"));
        set.add(new User(3, "C"));
        set.add(new User(4, "D"));

        Set<User> set2 = new HashSet<>();
        set2.add(new User(1, "A"));
        set2.add(new User(2, "B"));

        set.retainAll(set2);
        for (User us : set) {
            System.out.println(us.toString());
        }
    }

}
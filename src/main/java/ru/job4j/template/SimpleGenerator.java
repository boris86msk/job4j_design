package ru.job4j.template;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleGenerator implements Generator {

    @Override
    public String produce(String template, Map<String, String> args) {
        return null;
    }

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\$\\{\\w+}");
        Matcher matcher = pattern.matcher("I am a ${name}, Who are ${subject}?");
        System.out.println(matcher.find());

    }
}

package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * преобразование объекта в json-строку:
 * Gson gson = new GsonBuilder().create();
 * gson.toJson(наш объект)
 */

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact(123456,"+7(924)111-111-11-11"),
                 new String[] {"Worker", "Married"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        /* Модифицируем json-строку */
        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
    }
}

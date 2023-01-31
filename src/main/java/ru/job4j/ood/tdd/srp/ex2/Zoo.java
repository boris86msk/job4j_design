package ru.job4j.ood.tdd.srp.ex2;

import java.util.List;


/*
   Возможно не очень логичное наследование классов но тут не об этом.
   Думаю нарушением SRP так же является расположение внутри класса
   классов-моделей данных. Класс-обработчик моделей данных отдельно,
   сами классы-модели отдельно. Исключением тут наверное будет реализация
   связанного списка или чего то подобного когда "классы-ноды" являются
   частью логики а не класическими моделями данных.
 */
public class Zoo {
    private List<Zoo> list;

    public void addAnimal(Zoo animal) {
        list.add(animal);
    }

    public void howAll() {
        list.forEach(System.out::println);
    }




    public class Leo extends Zoo {
        @Override
        public String toString() {
            return "Leo";
        }
    }
}

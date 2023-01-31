package ru.job4j.ood.tdd.srp.battle;

import java.util.Random;

public class Battle {
    public static void main(String[] args) throws InterruptedException {
        Warrior warrior1 = new Warrior();
        Warrior warrior2 = new Warrior();
        Random random = new Random();
        int hit = 1;
        while (warrior1.points > 0 && warrior2.points > 0) {
            System.out.format("удар № %d%n", hit);
            hit++;
            if (random.nextBoolean()) {
                warrior1.points -= 20;
                System.out.println("Атаковал первый воин");
            } else {
                warrior2.points -= 20;
                System.out.println("Атаковал второй воин");
            }
            System.out.format("Здоровье первого воина %d%n", warrior1.points);
            System.out.format("Здоровье второго воина %d%n", warrior2.points);
            Thread.sleep(2000);
        }
        System.out.format("%n----Победил %s воин----", warrior1.points == 0
                ? "второй" : "первый");
    }
}

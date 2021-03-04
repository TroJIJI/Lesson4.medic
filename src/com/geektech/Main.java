package com.geektech;

import java.util.Random;

public class Main {

    public static int[] heroesHealth = {270, 280, 250, 300};
    public static int[] heroesDamage = {20, 15, 25, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic"};
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int roundNumber = 0;


    public static void main(String[] args) {
        printStatistics();
        System.out.println("_______________________________");
        System.out.println("THE GAME IS STARTED " +
                "!!!FIGHT!!!");
        bossDefenceType = changeBossDefence();


        while (!isGameFinished()) {
            round();
        }


    }

    public static void round() {
        roundNumber++;
        System.out.println("__________ROUND " + roundNumber + "__________");
        System.out.println("THE ULTIMATE DAMAGE: " + bossDefenceType);
        bossHits();
        heroesHits();
        medicHealth();
        printStatistics();
    }

    public static void bossHits() {

        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                heroesHealth[i] = heroesHealth[i] - bossDamage;

            }

            if (heroesHealth[i] < 0) {
                heroesHealth[i] = 0;
            }
        }
    }


    public static void heroesHits() {
        Random random = new Random();
        int coeff = random.nextInt(8) + 2;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] > 0 && bossHealth > 0) {
                    if (heroesAttackType[i] == bossDefenceType) {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                        System.out.println("_____SUPER DAMAGE " + heroesDamage[i]
                                * coeff + "_____" + " [" + coeff + "]");
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                    if (bossHealth < 0) {
                        bossHealth = 0;
                    }
                }

            }
        }
    }

    public static void printStatistics() {
        System.out.println("Boss health " + bossHealth + "[Damage: " + bossDamage + "]");
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + " health : "
                    + heroesHealth[i] + "[Damage: " + heroesDamage[i] + "]");

        }

    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("__________HEROES WON__________");
            return true;
        }
        boolean allHeroesDead = true;
        for (int hero : heroesHealth) {
            if (hero > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("__________BOSS WON__________");
        }
        return allHeroesDead;
    }


    public static String changeBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(2);
        return heroesAttackType[randomIndex];

    }

    public static void medicHealth() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[3] > 0 && heroesHealth[i] < 100 && heroesHealth[i] > 0 && heroesAttackType[i] != "Medic") {
                Random r = new Random();
                int random = r.nextInt(29) + 1;
                heroesHealth[i] = heroesHealth[i] + random;
                System.out.println("Added: " + heroesAttackType[i] + random + "HP");
                break;
            }

        }
    }


}

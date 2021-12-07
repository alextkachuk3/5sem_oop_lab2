package com.labs.lab2.dance;

import java.util.Random;

/**
 * Random dances generator
 */
public class DanceGenerator {
    /**
     * Generates string with random letters
     * @param length - length of new string
     * @return random string
     */
    private static String randomString(int length){
        Random random = new Random();
        char[] word = new char[length];
        word[0] = (char)('A' + random.nextInt(26));
        for(int j = 1; j < word.length; j++)
        {
            word[j] = (char)('a' + random.nextInt(26));
        }
        return new String(word);
    }

    /**
     * Generates random string with random size(1-12)
     * @return random string
     */
    private static String randomString(){
        return randomString((int) (Math.random() % 12 + 1));
    }


    /**
     * @return random type
     */
    private static String randomType() {
        int index = (int)(Math.random() % 4);
        switch (index){
            case 0 -> {
                return "ballroom";
            }
            case 1 -> {
                return "folk";
            }
            case 2 -> {
                return "variety";
            }
            case 3 -> {
                return "oriental";
            }
            default -> {

            }
        }
        return "folk";
    }

    /**
     * @return random scene
     */
    private static String randomScene() {
        int index = (int)(Math.random() % 3);
        switch (index){
            case 0 -> {
                return "assemblyHall";
            }
            case 1 -> {
                return "streetPlatform";
            }
            case 2 -> {
                return "TVStudio";
            }
            default -> {

            }
        }
        return "TVStudio";
    }

    /**
     * @return random number of dancers
     */
    private static String randomNumberOfDancers() {
        int index = (int)(Math.random() % 3);
        switch (index){
            case 0 -> {
                return "massive";
            }
            case 1 -> {
                return "solo";
            }
            case 2 -> {
                return "pair";
            }
            default -> {

            }
        }
        return "massive";
    }

    /**
     * @return random music
     */
    private static String randomMusic() {
        int index = (int)(Math.random() % 2);
        switch (index){
            case 0 -> {
                return "Phonogram";
            }
            case 1 -> {
                return "Live";
            }
            default -> {

            }
        }
        return "Live";
    }



    /**
     * Generates dance number with random data
     * @return random dance number
     */
        public static DanceNumber randomDanceNumber(){
        Random random = new Random();
        var id = "dance" + random.nextInt(100000);
        var type = randomType();
        var scene = randomScene();
        var numberOfDancers = randomNumberOfDancers();
        var music = randomMusic();
        var name = randomString(random.nextInt(5) + 5);
        var age = Math.abs(random.nextInt() % 30) + 1;
        var origin = randomString(random.nextInt(3) + 4);
        var dancers = new Dancers(name, age, origin);
        var number = Math.abs(random.nextInt() % 20) + 1;
        return new DanceNumber(id, type, scene, numberOfDancers, music, dancers, number);
    }

    /**
     *
     * @param size - a number of dance numbers
     * @return dances with random dance numbers
     */
    public static Dances randomDances(int size) {
        Dances dances = new Dances();
        for(int i = 0; i < size; i++){
            dances.add(randomDanceNumber());
        }
        return dances;
    }

    /**
     * Generates dances with random dance number and size(1-30)
     * @return dances with random dance numbers
     */
    public static Dances randomDances(){
        return randomDances((int) (Math.random() % 30 + 1));
    }
}

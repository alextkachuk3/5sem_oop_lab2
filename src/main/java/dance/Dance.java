package com.labs.lab2.dance;

/**
 * Class of single XML Dance object
 * @author Alex
 */
public class Dance implements Comparable<Dance> {
    private String id;
    private Type type;
    private Scene scene;
    private NumberOfDancers numberOfDancers;
    private Music music;
    private Dancers dancers;
    private int number;

    public Dance(String _id, Type _type, Scene _scene,
                 NumberOfDancers _numberOfDancers, Music _music,
                 Dancers _dancers, int _number) {
        id = _id;
        type = _type;
        scene = _scene;
        numberOfDancers = _numberOfDancers;
        music = _music;
        dancers = _dancers;
        number = _number;
    }

    public String getId() {
        return id;
    }

    public void setId(String _id) {
        id = _id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type _type) {
        type = _type;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene _scene) {
        scene = _scene;
    }

    public NumberOfDancers getNumberOfDancers() {
        return numberOfDancers;
    }

    public void setNumberOfDancers(NumberOfDancers _numberOfDancers) {
        numberOfDancers = _numberOfDancers;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music _music) {
        music = _music;
    }

    public Dancers getDancers() {
        return dancers;
    }

    public void setDancers(Dancers _dancers) {
        dancers = _dancers;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int _number) {
        number = _number;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     */
    @Override
    public int compareTo(Dance o) {
        return id.compareTo(o.id);
    }

    @Override
    public String toString() {
        return "Stone{" +
                "id=" + id +
                ", type=" + type +
                ", scene=" + scene +
                ", numberOfDancers=" + numberOfDancers +
                ", music=" + music +
                ", dancers=" + dancers +
                ", number=" + number +
                '}';
    }
}
package com.labs.lab2.Dance;

/**
 * XML Dance data class
 * @author  Alex
 */
public class DanceNumber implements Comparable<DanceNumber> {
    private String id;
    private Type type;
    private Scene scene;
    private NumberOfDancers numberOfDancers;
    private Music music;
    private Dancers dancers;
    private int number;

    /**
     * @param _id Dance number id
     * @param _type Dance number type(genre)
     * @param _scene Dance number scene type
     * @param _numberOfDancers Dance number type by dancers count
     * @param _music Dance number music
     * @param _dancers Dance number dancers info
     * @param _number Number of dance number in the program
     */
    DanceNumber(String _id, Type _type, Scene _scene, NumberOfDancers _numberOfDancers,
                Music _music, Dancers _dancers, int _number) {
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

    @Override
    public int compareTo(DanceNumber o) {
        return id.compareTo(o.getId());
    }
}

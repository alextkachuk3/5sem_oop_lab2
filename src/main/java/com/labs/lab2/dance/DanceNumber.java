package com.labs.lab2.dance;

/**
 * XML Dance data class
 * @author  Alex
 */
public class DanceNumber implements Comparable<DanceNumber> {
    private String id;
    private String type;
    private String scene;
    private String numberOfDancers;
    private String music;
    private Dancers dancers;
    private int number;


    /**
     * Default DanceNumber constructor
     */
    public DanceNumber() {
        dancers = new Dancers();
    }

    /**
     * Parametrized DanceNumber constructor
     * @param _id Dance number id
     * @param _type Dance number type(genre)
     * @param _scene Dance number scene type
     * @param _numberOfDancers Dance number type by dancers count
     * @param _music Dance number music
     * @param _dancers Dance number dancers info
     * @param _number Number of dance number in the program
     */
    public DanceNumber(String _id, String _type, String _scene, String _numberOfDancers,
                String _music, Dancers _dancers, int _number) {
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

    public String getType() {
        return type;
    }

    public void setType(String _type) {
        type = _type;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String _scene) {
        scene = _scene;
    }

    public String getNumberOfDancers() {
        return numberOfDancers;
    }

    public void setNumberOfDancers(String _numberOfDancers) {
        numberOfDancers = _numberOfDancers;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String _music) {
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

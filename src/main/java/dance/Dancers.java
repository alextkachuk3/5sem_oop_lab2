package com.labs.lab2.dance;

/**
 * Dancers params class
 * @author  Alex
 */
public class Dancers {
    private String name;
    private int age;
    private String origin;

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int _age) {
        age = _age;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String _origin) {
        origin = _origin;
    }

    @Override
    public String toString() {
        return "Dancers{" +
                ", name=" + name +
                ", age=" + age +
                ", origin='" + origin + '\'' +
                '}';
    }
}

package com.labs.lab2.dance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Dance XML objects class
 * @author Alex
 */
public class Dances {
    private List<DanceNumber> dances = new ArrayList<>();

    public Dances() {}

    public Dances(List<DanceNumber> danceNumbers) {
        dances = danceNumbers;
    }

    public List<DanceNumber> getDances() {
        return dances;
    }

    public void setDances(List<DanceNumber> _dances) {
        dances = _dances;
    }

    public boolean add(DanceNumber danceNumber) {
        return dances.add(danceNumber);
    }

    public boolean remove(String id) {
        return dances.removeIf(dance -> Objects.equals(dance.getId(), id));
    }

    @Override
    public String toString() {
        return "Dance=" + dances;
    }

    public void sort() {
        Collections.sort(dances);
    }
}

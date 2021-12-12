package com.labs.lab2.dance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public boolean add(DanceNumber danceNumber) {
        return dances.add(danceNumber);
    }

    public void sort() {
        Collections.sort(dances);
    }
}

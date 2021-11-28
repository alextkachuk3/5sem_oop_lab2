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
    private List<Dance> dances = new ArrayList<>();

    public boolean add(Dance dance) {
        return dances.add(dance);
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

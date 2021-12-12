package com.labs.lab2.parsers;

import com.labs.lab2.dance.DanceNumber;
import com.labs.lab2.dance.Dances;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DanceSortTest {
    @Test
    public void sortDances() {
        Dances dances = new Dances();
        DanceNumber danceNumber1 = new DanceNumber();
        danceNumber1.setId("dance33");
        DanceNumber danceNumber2 = new DanceNumber();
        danceNumber2.setId("dance12");
        DanceNumber danceNumber3 = new DanceNumber();
        danceNumber3.setId("dance75");
        dances.add(danceNumber1);
        dances.add(danceNumber2);
        dances.add(danceNumber3);
        dances.sort();
        assertEquals(dances.getDances().get(0).getId(), "dance12");
        assertEquals(dances.getDances().get(1).getId(), "dance33");
        assertEquals(dances.getDances().get(2).getId(), "dance75");
    }
}

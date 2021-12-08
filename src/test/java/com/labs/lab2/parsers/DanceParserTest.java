package com.labs.lab2.parsers;

import com.labs.lab2.DanceXMLTest;
import com.labs.lab2.dance.Dances;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class DanceParserTest extends DanceXMLTest {

    @Test
    public void readGemFromXmlFile() {
        Dances readDance = parser.readDancesFromXMLFile(inputPath);
        assertNotNull(readDance);
        assertEquals(size, readDance.getDances().size());
        for(int i = 0; i < size; i++){
            assertEquals(dances.getDances().get(i), readDance.getDances().get(i));
        }
    }
}

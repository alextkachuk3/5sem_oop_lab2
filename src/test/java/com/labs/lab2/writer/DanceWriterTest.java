package com.labs.lab2.writer;

import com.labs.lab2.DanceXMLTest;
import com.labs.lab2.dance.DanceGenerator;
import com.labs.lab2.dance.Dances;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public abstract class DanceWriterTest extends DanceXMLTest {
    @Test
    public void writeGemToXmlFile() {
        Dances writeDance = DanceGenerator.randomDances(size);
        writer.writeDancesToXMLFile(writeDance, outputPath);
        Dances readDance = parser.readDancesFromXMLFile(outputPath);
        for(int i = 0; i < size; i++){
            assertEquals(writeDance.getDances().get(i), readDance.getDances().get(i));
        }
    }
}

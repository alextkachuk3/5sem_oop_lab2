package com.labs.lab2.test.parsers;

import com.labs.lab2.dance.DanceGenerator;
import com.labs.lab2.dance.Dances;
import com.labs.lab2.dance.parsers.DanceParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public abstract class DanceParserTest {
    protected static String schemaPath="Dance.xsd";
    protected static String inputPath = "src/main/java/com/labs/lab2/test/data/input.xml";
    protected static String outputPath = "src/main/java/com/labs/lab2/test/data/output.xml";
    protected static DanceParser parser;
    protected static Dances dances;
    protected static int size = 10;

    @Before
    public void deleteFiles() {

    }
    @After
    public void tearDown() {
        parser = null;
        dances = null;

    }
    @Test
    public void writeGemToXmlFile() {
        Dances writeDance = DanceGenerator.randomDances(size);
        parser.writeDancesToXMLFile(writeDance, outputPath);
        Dances readDance = parser.readDancesFromXMLFile(outputPath);
        for(int i = 0; i < size; i++){
            assertEquals(writeDance.getDances().get(i), readDance.getDances().get(i));
        }
    }

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

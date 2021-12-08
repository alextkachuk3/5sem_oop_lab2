package com.labs.lab2.parsers;

import com.labs.lab2.dance.DanceGenerator;
import com.labs.lab2.dance.xml.parsers.StAXDanceParser;
import com.labs.lab2.dance.xml.writers.StAXDanceWriter;
import org.junit.jupiter.api.BeforeEach;


public class StAXDanceParserTest extends DanceParserTest {

    @BeforeEach
    public void setUp() {
        parser = new StAXDanceParser(schemaPath);
        writer = new StAXDanceWriter(schemaPath);
        dances = DanceGenerator.randomDances(size);
        writer.writeDancesToXMLFile(dances, inputPath);
    }
}
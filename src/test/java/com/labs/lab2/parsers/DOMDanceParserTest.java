package com.labs.lab2.parsers;

import com.labs.lab2.dance.DanceGenerator;
import com.labs.lab2.dance.xml.parsers.DOMDanceParser;
import com.labs.lab2.dance.xml.writers.DOMDanceWriter;
import org.junit.jupiter.api.BeforeEach;

public class DOMDanceParserTest extends DanceParserTest{

    @BeforeEach
    public void setUp() {
        parser = new DOMDanceParser(schemaPath);
        writer = new DOMDanceWriter(schemaPath);
        dances = DanceGenerator.randomDances(size);
        writer.writeDancesToXMLFile(dances, inputPath);
    }
}
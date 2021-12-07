package com.labs.lab2.test.parsers;

import com.labs.lab2.dance.DanceGenerator;
import com.labs.lab2.dance.parsers.DOMDanceParser;
import org.junit.Before;

public class DOMDanceParserTest extends DanceParserTest{

    @Before
    public void setUp() {
        parser = new DOMDanceParser(schemaPath);
        dances = DanceGenerator.randomDances(size);
        parser.writeDancesToXMLFile(dances, inputPath);
    }
}
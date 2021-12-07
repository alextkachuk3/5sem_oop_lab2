package com.labs.lab2.test.parsers;

import com.labs.lab2.dance.DanceGenerator;
import com.labs.lab2.dance.parsers.StAXDanceParser;
import org.junit.Before;


public class StAXDanceParserTest extends DanceParserTest {

    @Before
    public void setUp() {
        parser = new StAXDanceParser(schemaPath);
        dances = DanceGenerator.randomDances(size);
        parser.writeDancesToXMLFile(dances, inputPath);
    }
}
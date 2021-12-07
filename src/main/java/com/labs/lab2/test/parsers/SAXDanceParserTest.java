package com.labs.lab2.test.parsers;

import com.labs.lab2.dance.DanceGenerator;
import com.labs.lab2.dance.parsers.SAXDanceParser;
import org.junit.Before;

public class SAXDanceParserTest extends DanceParserTest{

    @Before
    public void setUp() {
        parser = new SAXDanceParser(schemaPath);
        dances = DanceGenerator.randomDances(size);
        parser.writeDancesToXMLFile(dances, inputPath);
    }
}
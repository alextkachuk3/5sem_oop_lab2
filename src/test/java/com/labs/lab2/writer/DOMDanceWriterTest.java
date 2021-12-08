package com.labs.lab2.writer;

import com.labs.lab2.dance.DanceGenerator;
import com.labs.lab2.dance.xml.parsers.DOMDanceParser;
import com.labs.lab2.dance.xml.writers.DOMDanceWriter;
import org.junit.jupiter.api.BeforeEach;

public class DOMDanceWriterTest extends DanceWriterTest {

    @BeforeEach
    void setUp() {
        parser = new DOMDanceParser(schemaPath);
        writer = new DOMDanceWriter(schemaPath);
        dances = DanceGenerator.randomDances(size);
        writer.writeDancesToXMLFile(dances, inputPath);
    }
}
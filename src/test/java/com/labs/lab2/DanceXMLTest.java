package com.labs.lab2;

import com.labs.lab2.dance.Dances;
import com.labs.lab2.dance.xml.parsers.DanceParser;
import com.labs.lab2.dance.xml.writers.DanceWriter;
import org.junit.jupiter.api.AfterEach;

public abstract class DanceXMLTest {
    protected static String schemaPath="Dance.xsd";
    protected static String inputPath = "src/test/java/com/labs/lab2/data/input.xml";
    protected static String outputPath = "src/test/java/com/labs/lab2/data/output.xml";
    protected static DanceParser parser;
    protected static DanceWriter writer;
    protected static Dances dances;
    protected static int size = 10;

    @AfterEach
    public void tearDown() {
        parser = null;
        dances = null;

    }


}

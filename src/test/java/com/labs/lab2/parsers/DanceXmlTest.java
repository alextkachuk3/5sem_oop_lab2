package com.labs.lab2.parsers;

import com.labs.lab2.dance.Dances;
import com.labs.lab2.dance.xml.parsers.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public abstract class DanceXmlTest {
    protected static String schemaPath="Dance.xsd";
    protected static String inputPath = "Dance.xml";
    protected static Parser parser;
    protected static Dances dances;

    @AfterEach
    public void tearDown() {
        parser = null;
        dances = null;

    }

    @Test
    public void readGemFromXmlFile() throws XMLStreamException, IOException, ParserConfigurationException, SAXException {
        Dances readDance = parser.parse(new File(inputPath));
        assertNotNull(readDance);
        assertEquals(readDance.getDances().get(0).getId(), "dance0");
        assertEquals(readDance.getDances().get(0).getType(), "folk");
        assertEquals(readDance.getDances().get(0).getScene(), "assemblyHall");
        assertEquals(readDance.getDances().get(0).getNumberOfDancers(), "solo");
        assertEquals(readDance.getDances().get(0).getMusic(), "Live");
        assertEquals(readDance.getDances().get(0).getDancers().getName(), "string");
        assertEquals(readDance.getDances().get(0).getDancers().getAge(), 8);
        assertEquals(readDance.getDances().get(0).getDancers().getOrigin(), "Ukraine");
        assertEquals(readDance.getDances().get(0).getNumber(), 12);

        assertEquals(readDance.getDances().get(1).getId(), "dance1");
        assertEquals(readDance.getDances().get(1).getType(), "ballroom");
        assertEquals(readDance.getDances().get(1).getScene(), "streetPlatform");
        assertEquals(readDance.getDances().get(1).getNumberOfDancers(), "solo");
        assertEquals(readDance.getDances().get(1).getMusic(), "Phonogram");
        assertEquals(readDance.getDances().get(1).getDancers().getName(), "string");
        assertEquals(readDance.getDances().get(1).getDancers().getAge(), 19);
        assertEquals(readDance.getDances().get(1).getDancers().getOrigin(), "USA");
        assertEquals(readDance.getDances().get(1).getNumber(), 14);
    }


}
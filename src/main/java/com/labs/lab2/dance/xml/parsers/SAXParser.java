package com.labs.lab2.dance.xml.parsers;

import com.labs.lab2.dance.Dances;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SAXParser extends Parser {
    public Dances parse(File xml) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
        DanceHandler handler = new DanceHandler();
        saxParser.parse(xml, handler);
        return new Dances(handler.getListOfDanceNumbers());
    }
}

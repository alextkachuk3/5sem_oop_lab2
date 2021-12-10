package com.labs.lab2;

import com.labs.lab2.dance.Dances;
import com.labs.lab2.dance.xml.parsers.DOMParser;
import com.labs.lab2.dance.xml.parsers.SAXParser;
import com.labs.lab2.dance.xml.parsers.StAXParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;

/**
 * @author  Alex
 */
public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, XMLStreamException {
        File file = new File("Dance.xml");
        DOMParser domParser = new DOMParser();
        Dances dances = domParser.parse(file);

        SAXParser saxParser = new SAXParser();
        Dances dances1 = saxParser.parse(file);

        StAXParser staxParser = new StAXParser();
        Dances dances2 = staxParser.parse(file);


        System.out.println("Hello, World!");
    }
}

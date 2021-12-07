package com.labs.lab2;

import com.labs.lab2.dance.Dances;
import com.labs.lab2.dance.parsers.DOMDanceParser;
import com.labs.lab2.dance.parsers.SAXDanceParser;
import com.labs.lab2.dance.parsers.StAXDanceParser;

/**
 * @author  Alex
 */
public class Main {
    public static void main(String[] args)
    {
        DOMDanceParser domDanceParser = new DOMDanceParser("Dance.xsd");
        Dances dances = domDanceParser.readDancesFromXMLFile("Dance.xml");
        StAXDanceParser stAXDanceParser = new StAXDanceParser("Dance.xsd");
        Dances dances1 = stAXDanceParser.readDancesFromXMLFile("Dance.xml");
        SAXDanceParser saxDanceParser = new SAXDanceParser("Dance.xsd");
        Dances dances2 = saxDanceParser.readDancesFromXMLFile("Dance.xml");
        System.out.println("Hello, World!");
    }
}

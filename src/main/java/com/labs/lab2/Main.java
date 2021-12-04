package com.labs.lab2;

import com.labs.lab2.dance.Dances;
import com.labs.lab2.dance.parsers.DOMDanceParser;
import com.labs.lab2.dance.parsers.StAXGemsParser;

/**
 * @author  Alex
 */
public class Main {
    public static void main(String[] args)
    {
        DOMDanceParser domDanceParser = new DOMDanceParser("Dance.xsd");
        Dances dances = domDanceParser.readDancesFromXmlFile("Dance.xml");
        StAXGemsParser stAXGemsParser = new StAXGemsParser("Dance.xsd");
        Dances dances1 = stAXGemsParser.readDancesFromXmlFile("Dance.xml");
        System.out.println("Hello, World!");
    }
}

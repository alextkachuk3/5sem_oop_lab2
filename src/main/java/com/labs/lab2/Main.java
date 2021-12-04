package com.labs.lab2;

import com.labs.lab2.dance.Dances;
import com.labs.lab2.dance.parsers.DOMDanceParser;

/**
 * @author  Alex
 */
public class Main {
    public static void main(String[] args)
    {
        DOMDanceParser domDanceParser = new DOMDanceParser("Dance.xsd");
        Dances dances = domDanceParser.readDancesFromXmlFile("Dance.xml");
        System.out.println("Hello, World!");
    }
}

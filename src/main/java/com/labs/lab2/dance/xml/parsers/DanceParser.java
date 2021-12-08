package com.labs.lab2.dance.xml.parsers;

import com.labs.lab2.dance.Dances;
import com.labs.lab2.dance.DanceHandler;
import com.labs.lab2.dance.DanceValidator;
import com.labs.lab2.dance.xml.DanceXML;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Dance parser abstract class
 */
public abstract class DanceParser extends DanceXML {
    /**
     * Dance parser constructor
     * @param schemaPath - path to XSD schema for validating XML file
     */
    public DanceParser(String schemaPath) {
       super(schemaPath);
    }

    /**
     * Reads dance numbers from XML file
     * @param filePath - path to XML file
     * @return dances if file is correct, else null
     */
    public abstract Dances readDancesFromXMLFile(String filePath);


}

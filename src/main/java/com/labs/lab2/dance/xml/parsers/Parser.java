package com.labs.lab2.dance.xml.parsers;

import com.labs.lab2.dance.Dances;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Parser {

    /**
     * Reads dance numbers from XML file
     * @param xml - XML file
     * @return dances if file is correct, else null
     */
    public abstract Dances parse(File xml) throws IOException, XMLStreamException, ParserConfigurationException, SAXException;


}
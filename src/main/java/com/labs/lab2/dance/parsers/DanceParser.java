package com.labs.lab2.dance.parsers;

import com.labs.lab2.dance.Dances;
import com.labs.lab2.dance.DanceHandler;
import com.labs.lab2.dance.DanceValidator;
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
public abstract class DanceParser {
    protected DanceValidator danceValidator;
    protected DanceHandler danceHandler;

    /**
     * Dance parser constructor
     * @param schemaPath - path to XSD schema for validating XML file
     */
    public DanceParser(String schemaPath) {
        this.danceHandler = new DanceHandler();
        try {
            this.danceValidator = new DanceValidator(schemaPath, danceHandler);
        } catch (SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get XSD validation errors
     * @return XSD errors after reading/writing
     */
    public List<String> getErrors() {
        return danceHandler.getErrors();
    }

    /**
     * Writes dance numbers to XML file
     * @param dances - dances for write
     * @param filePath - path to XML file
     * @return true if success, else false
     */
    public abstract boolean writeDancesToXmlFile(Dances dances, String filePath);

    /**
     * Reads dance numbers from XML file
     * @param filePath - path to XML file
     * @return dances if file is correct, else null
     */
    public abstract Dances readDancesFromXmlFile(String filePath);

    /**
     * Converts XML file to multi-line
     * @param filePath - path to xml file
     * @param source DOM or SAX source
     */
    protected void transformFile(String filePath, Source source){
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            Result result = new StreamResult(new FileWriter(filePath));
            transformer.transform(source, result);
        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }
    }
}

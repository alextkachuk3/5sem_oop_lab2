package com.labs.lab2.dance.xml;

import com.labs.lab2.dance.DanceHandler;
import com.labs.lab2.dance.DanceValidator;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public abstract class DanceXML {
    protected DanceValidator danceValidator;
    protected DanceHandler danceHandler;

    public DanceXML(String schemaPath) {
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

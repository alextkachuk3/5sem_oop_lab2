package com.labs.lab2.Dance;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class DanceValidator {
    private final DefaultHandler handler;
    private final SAXParser parser;
    
    /**
     * @param schemaPath - path to xsd file
     * @param _handler - errors handler
     */
    public DanceValidator(String schemaPath, DefaultHandler _handler) throws SAXException, ParserConfigurationException {
        handler = _handler;
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(schemaPath));
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setSchema(schema);
        parser = spf.newSAXParser();
    }
    /**
     * Checks if XML file match to XSD.
     * @param filePath - path to xml file
     */
    public void validate(String filePath) throws IOException, SAXException {
        parser.parse(filePath, handler);
    }
}

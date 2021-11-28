package com.labs.lab2.Dance;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * XML schema validation handle
 * @author Alex
 */
public class DanceHandler extends DefaultHandler {
    private List<String> errors = new ArrayList<>();

    public DanceHandler() {
        super();
    }

    private void log(String text){
        errors.add(text);
    }

    @Override
    public String toString() {
        return "Dance handler errors: " + errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public void warning(SAXParseException e){
        log("WARNING "+getErrorAddress(e) + " " + e.getMessage());
    }

    @Override
    public void error(SAXParseException e){
        log("ERROR "+getErrorAddress(e) + " " + e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e){
        log("FATAL ERROR "+getErrorAddress(e) + " " + e.getMessage());
    }

    private String getErrorAddress(SAXParseException e){
        return e.getLineNumber() + ":"+e.getColumnNumber();
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        errors = new ArrayList<>();
    }
}

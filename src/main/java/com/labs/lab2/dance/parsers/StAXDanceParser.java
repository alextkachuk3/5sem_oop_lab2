package com.labs.lab2.dance.parsers;

import com.labs.lab2.dance.DanceNumber;
import com.labs.lab2.dance.DanceXmlTags;
import com.labs.lab2.dance.Dancers;
import com.labs.lab2.dance.Dances;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.*;
import javax.xml.transform.dom.DOMSource;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Dance XML file StAX Parser
 * @author Alex
 */
public class StAXDanceParser extends DanceParser {

    private final XMLInputFactory inputFactory;
    /**
     * Dance parser constructor
     *
     * @param schemaPath - path to XSD schema for validating XML file
     */
    public StAXDanceParser(String schemaPath) {
        super(schemaPath);
        inputFactory = XMLInputFactory.newInstance();
    }

    /**
     * Writes dance numbers to XML file
     *
     * @param dances   - dances for write
     * @param filePath - path to XML file
     * @return true if success, else false
     */
    @Override
    public boolean writeDancesToXMLFile(Dances dances, String filePath) {
        if(dances == null) return false;
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter xmlStreamWriter = null;
        try {
            xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileWriter(filePath));
            xmlStreamWriter.writeStartDocument();
            xmlStreamWriter.writeStartElement(DanceXmlTags.DANCE.getValue());

            for(var danceNumber : dances.getDances()) {
                writeDanceNumber(danceNumber, xmlStreamWriter);
            }

            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.flush();
            transform(filePath);
            danceValidator.validate(filePath);
        } catch (IOException | SAXException | ParserConfigurationException | XMLStreamException e) {
            e.printStackTrace();
        } finally {
            try {
                if(xmlStreamWriter != null) {
                    xmlStreamWriter.close();
                }
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
        return danceHandler.getErrors().size() == 0;
    }

    private void writeDanceNumber(DanceNumber danceNumber, XMLStreamWriter xmlStreamWriter) throws XMLStreamException {
        xmlStreamWriter.writeStartElement(DanceXmlTags.DANCENUMBER.getValue());

        //attributes
        xmlStreamWriter.writeAttribute(DanceXmlTags.ID.getValue(), danceNumber.getId());
        //type
        writeElement(xmlStreamWriter, DanceXmlTags.TYPE.getValue(), danceNumber.getType());
        //scene
        writeElement(xmlStreamWriter, DanceXmlTags.SCENE.getValue(), danceNumber.getScene());
        //number of dancers
        writeElement(xmlStreamWriter, DanceXmlTags.NUMBEROFDANCERS.getValue(), danceNumber.getNumberOfDancers());
        //music
        writeElement(xmlStreamWriter, DanceXmlTags.MUSIC.getValue(), danceNumber.getMusic());
        //dancers
        writeDancers(xmlStreamWriter, danceNumber.getDancers());
        //number
        writeElement(xmlStreamWriter, DanceXmlTags.NUMBER.getValue(), Integer.toString(danceNumber.getNumber()));

        xmlStreamWriter.writeEndElement();
    }

    private void writeDancers(XMLStreamWriter xmlStreamWriter, Dancers dancers) throws XMLStreamException {
        xmlStreamWriter.writeStartElement(DanceXmlTags.DANCERS.getValue());

        //name
        writeElement(xmlStreamWriter, DanceXmlTags.NAME.getValue(), dancers.getName());
        //age
        writeElement(xmlStreamWriter, DanceXmlTags.AGE.getValue(), Integer.toString(dancers.getAge()));
        //origin
        writeElement(xmlStreamWriter, DanceXmlTags.ORIGIN.getValue(), dancers.getOrigin());

        xmlStreamWriter.writeEndElement();

    }

    private void writeElement(XMLStreamWriter xmlStreamWriter, String element, String val) throws XMLStreamException {
        xmlStreamWriter.writeStartElement(element);
        xmlStreamWriter.writeCharacters(val);
        xmlStreamWriter.writeEndElement();
    }

    private void transform(String filePath) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        transformFile(filePath,
                new DOMSource(
                        builder.parse(
                                new InputSource(
                                        new InputStreamReader(
                                                new FileInputStream(filePath))))));
    }

    /**
     * Reads dance numbers from XML file
     *
     * @param filePath - path to XML file
     * @return dances if file is correct, else null
     */
    @Override
    public Dances readDancesFromXMLFile(String filePath) {
        try {
            danceValidator.validate(filePath);
            if(danceHandler.getErrors().size() == 0)
            {
                Dances dances = new Dances();
                XMLStreamReader reader;
                String name;
                try(FileInputStream inputStream = new FileInputStream(filePath)) {
                    reader = inputFactory.createXMLStreamReader(inputStream);
                    while(reader.hasNext()) {
                        int type = reader.next();
                        if(type == XMLStreamConstants.START_ELEMENT) {
                            name = reader.getLocalName();
                            if (name.equals(DanceXmlTags.DANCENUMBER.getValue())) {
                                dances.add(readDanceNumber(reader));
                            }
                        }
                    }
                    return dances;
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }

        return null;
    }

    private DanceNumber readDanceNumber(XMLStreamReader reader) throws XMLStreamException {
        DanceNumber danceNumber = new DanceNumber();
        //ID
        danceNumber.setId(reader.getAttributeValue(null, DanceXmlTags.ID.getValue()));

        String name;
        while(reader.hasNext()) {
            int type = reader.next();
            switch(type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    switch (DanceXmlTags.valueOf(name.toUpperCase())) {
                        case TYPE -> danceNumber.setType(getXMLText(reader));
                        case SCENE -> danceNumber.setScene(getXMLText(reader));
                        case NUMBEROFDANCERS -> danceNumber.setNumberOfDancers(getXMLText(reader));
                        case MUSIC -> danceNumber.setMusic(getXMLText(reader));
                        case DANCERS -> danceNumber.setDancers(readDancers(reader));
                        case NUMBER -> danceNumber.setNumber(Integer.parseInt(getXMLText(reader)));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    if(DanceXmlTags.valueOf(name.toUpperCase()) == DanceXmlTags.DANCENUMBER) {
                        return danceNumber;
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag <" + DanceXmlTags.DANCENUMBER +">");
    }

    private Dancers readDancers(XMLStreamReader reader) throws XMLStreamException {
        Dancers dancers = new Dancers();
        int type;
        String name;
        while(reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    switch (DanceXmlTags.valueOf(name.toUpperCase())) {
                        case NAME -> dancers.setName(getXMLText(reader));
                        case AGE -> dancers.setAge(Integer.parseInt(getXMLText(reader)));
                        case ORIGIN -> dancers.setOrigin(getXMLText(reader));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    if (DanceXmlTags.valueOf(name.toUpperCase()) == DanceXmlTags.DANCERS) {
                        return dancers;
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag <" + DanceXmlTags.DANCERS + ">");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}

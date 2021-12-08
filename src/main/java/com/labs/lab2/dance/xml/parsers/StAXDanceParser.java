package com.labs.lab2.dance.xml.parsers;

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

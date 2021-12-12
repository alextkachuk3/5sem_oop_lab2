package com.labs.lab2.dance.xml.parsers;

import com.labs.lab2.dance.Dances;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class StAXParser extends Parser{

    /**
     * Reads dance numbers from XML file
     * @param xml - XML file
     * @return dance numbers from file
     */
    public Dances parse(File xml) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(xml));

        DanceHandler danceHandler = new DanceHandler();

        while (reader.hasNext()) {
            XMLEvent nextEvent = reader.nextEvent();

            if (nextEvent.isStartElement()) {
                StartElement startElement = nextEvent.asStartElement();

                nextEvent = reader.nextEvent();
                String name = startElement.getName().getLocalPart();

                if (nextEvent.isCharacters()) {
                    List<Attribute> attributes = new ArrayList<>();
                    Iterator<Attribute> iterator = startElement.getAttributes();
                    while (iterator.hasNext()) {
                        attributes.add(iterator.next());
                    }
                    Map<String, String> attributeMap = new HashMap<>();

                    for(Attribute attribute: attributes) {
                        attributeMap.put(attribute.getName().getLocalPart(), attribute.getValue());
                    }
                    danceHandler.setField(name, nextEvent.asCharacters().getData(), attributeMap);
                }
            }
        }

        return new Dances(danceHandler.getListOfDanceNumbers());
    }
}

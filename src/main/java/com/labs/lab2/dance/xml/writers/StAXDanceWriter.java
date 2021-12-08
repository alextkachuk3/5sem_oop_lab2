package com.labs.lab2.dance.xml.writers;

import com.labs.lab2.dance.DanceNumber;
import com.labs.lab2.dance.DanceXmlTags;
import com.labs.lab2.dance.Dancers;
import com.labs.lab2.dance.Dances;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.dom.DOMSource;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class StAXDanceWriter extends DanceWriter {
    public StAXDanceWriter(String schemaPath) {
        super(schemaPath);
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
}

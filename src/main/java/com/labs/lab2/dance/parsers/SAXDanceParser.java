package com.labs.lab2.dance.parsers;

import com.labs.lab2.dance.DanceNumber;
import com.labs.lab2.dance.DanceXmlTags;
import com.labs.lab2.dance.Dances;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Dance XML file SAX Parser
 * @author Alex
 */
public class SAXDanceParser extends StAXGemsParser {

    /**
     * Dance parser constructor
     *
     * @param schemaPath - path to XSD schema for validating XML file
     */
    public SAXDanceParser(String schemaPath) {
        super(schemaPath);
    }

    @Override
    public Dances readDancesFromXMLFile(String filePath)
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            danceValidator.validate(filePath);
            if(danceHandler.getErrors().size() == 0) {
                SAXParser saxParser = factory.newSAXParser();
                var reader = saxParser.getXMLReader();
                DanceSAXHandler readHandler = new DanceSAXHandler();
                reader.setContentHandler(readHandler);
                reader.parse(filePath);
                return new Dances(readHandler.getDanceNumbers());
            }
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class DanceSAXHandler extends DefaultHandler {
        private final List<DanceNumber> danceNumbers;
        private DanceNumber currentDanceNumber;
        private DanceXmlTags currentTag;
        private final EnumSet<DanceXmlTags> withText;
        private final static String DANCE_NUMBER_TAG = DanceXmlTags.DANCENUMBER.getValue();

        public DanceSAXHandler() {
            super();
            danceNumbers = new ArrayList<>();
            withText = EnumSet.range(DanceXmlTags.DANCENUMBER, DanceXmlTags.NUMBER);
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if(DANCE_NUMBER_TAG.equals(qName)) {
                currentDanceNumber = new DanceNumber();
                currentDanceNumber.setId(attributes.getValue(DanceXmlTags.ID.getValue()));
            } else {
                DanceXmlTags temp = DanceXmlTags.valueOf(qName.toUpperCase());
                if(withText.contains(temp)) {
                   currentTag = temp;
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (DANCE_NUMBER_TAG.equals(qName)) {
                danceNumbers.add(currentDanceNumber);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            String data = new String(ch, start, length).strip();
            if(currentTag!=null) {
                switch (currentTag) {
                    case TYPE -> currentDanceNumber.setType(data);
                    case SCENE -> currentDanceNumber.setScene(data);
                    case NUMBEROFDANCERS -> currentDanceNumber.setNumberOfDancers(data);
                    case MUSIC -> currentDanceNumber.setMusic(data);
                    case NAME -> currentDanceNumber.getDancers().setName(data);
                    case AGE -> currentDanceNumber.getDancers().setAge(Integer.parseInt(data));
                    case ORIGIN -> currentDanceNumber.getDancers().setOrigin(data);
                    case NUMBER -> currentDanceNumber.setNumber(Integer.parseInt(data));
                    default -> throw new EnumConstantNotPresentException(
                            currentTag.getDeclaringClass(), currentTag.name());
                }
            }
            currentTag = null;
        }

        public List<DanceNumber> getDanceNumbers() { return danceNumbers; }

    }

}

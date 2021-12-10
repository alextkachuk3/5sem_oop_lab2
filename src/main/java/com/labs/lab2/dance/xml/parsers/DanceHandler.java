package com.labs.lab2.dance.xml.parsers;

import com.labs.lab2.dance.DanceNumber;
import com.labs.lab2.dance.DanceXmlTags;
import com.labs.lab2.dance.Dancers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DanceHandler extends DefaultHandler {
    public String elementValue;
    private List<DanceNumber> listOfDanceNumbers = new ArrayList<>();

    public List<DanceNumber> getListOfDanceNumbers() {
        return listOfDanceNumbers;
    }

    @Override
    public void startDocument() throws SAXException {
        listOfDanceNumbers = new ArrayList<>();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        elementValue = new String(ch, start, length);
    }

    public String getName() {
        return DanceXmlTags.DANCENUMBER;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case DanceXmlTags.DANCENUMBER:
                DanceNumber danceNumber = new DanceNumber();
                danceNumber.setId(attributes.getValue(DanceXmlTags.ID));
                listOfDanceNumbers.add(danceNumber);
                break;
            case DanceXmlTags.DANCERS:
                Dancers dancers = new Dancers();
                getLastDanceNumber().setDancers(dancers);
        }
    }

    private DanceNumber getLastDanceNumber() {
        return listOfDanceNumbers.get(listOfDanceNumbers.size() - 1);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case DanceXmlTags.TYPE:
                getLastDanceNumber().setType(elementValue);
                break;
            case DanceXmlTags.SCENE:
                getLastDanceNumber().setScene(elementValue);
                break;
            case DanceXmlTags.NUMBEROFDANCERS:
                getLastDanceNumber().setNumberOfDancers(elementValue);
                break;
            case DanceXmlTags.MUSIC:
                getLastDanceNumber().setMusic(elementValue);
                break;
            case DanceXmlTags.NAME:
                getLastDanceNumber().getDancers().setName(elementValue);
                break;
            case DanceXmlTags.AGE:
                getLastDanceNumber().getDancers().setAge(Integer.parseInt(elementValue));
                break;
            case DanceXmlTags.ORIGIN:
                getLastDanceNumber().getDancers().setOrigin(elementValue);
                break;
            case DanceXmlTags.NUMBER:
                getLastDanceNumber().setNumber(Integer.parseInt(elementValue));
                break;
        }
    }

    public void setField(String qName, String content, Map<String, String> attributes) {
        switch (qName) {
            case DanceXmlTags.DANCENUMBER:
                DanceNumber danceNumber = new DanceNumber();
                danceNumber.setId(attributes.get(DanceXmlTags.ID));
                listOfDanceNumbers.add(danceNumber);
                break;
            case DanceXmlTags.TYPE:
                getLastDanceNumber().setType(content);
                break;
            case DanceXmlTags.SCENE:
                getLastDanceNumber().setScene(content);
                break;
            case DanceXmlTags.NUMBEROFDANCERS:
                getLastDanceNumber().setNumberOfDancers(content);
                break;
            case DanceXmlTags.MUSIC:
                getLastDanceNumber().setMusic(content);
                break;
            case DanceXmlTags.DANCERS:
                Dancers dancers = new Dancers();
                getLastDanceNumber().setDancers(dancers);
                break;
            case DanceXmlTags.NAME:
                getLastDanceNumber().getDancers().setName(content);
                break;
            case DanceXmlTags.AGE:
                getLastDanceNumber().getDancers().setAge(Integer.parseInt(content));
                break;
            case DanceXmlTags.ORIGIN:
                getLastDanceNumber().getDancers().setOrigin(content);
                break;
            case DanceXmlTags.NUMBER:
                getLastDanceNumber().setNumber(Integer.parseInt(content));
                break;
        }
    }
}
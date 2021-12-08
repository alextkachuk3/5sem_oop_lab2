package com.labs.lab2.dance.xml.writers;

import com.labs.lab2.dance.DanceNumber;
import com.labs.lab2.dance.DanceXmlTags;
import com.labs.lab2.dance.Dancers;
import com.labs.lab2.dance.Dances;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import java.io.IOException;

public class DOMDanceWriter extends DanceWriter {

    private DocumentBuilder docBuilder;
    public DOMDanceWriter(String schemaPath) {
        super(schemaPath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean writeDancesToXMLFile(Dances _dances, String filePath){
        if(_dances == null) return false;
        Document document = docBuilder.newDocument();
        Element rootElement = document.createElement(DanceXmlTags.DANCE.getValue());
        document.appendChild(rootElement);
        var dances = _dances.getDances();
        for(var dance:dances){
            rootElement.appendChild(createDanceNumberElement(dance,document));
        }
        transformFile(filePath, new DOMSource(document));
        try {
            danceValidator.validate(filePath);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        return danceHandler.getErrors().size() == 0;
    }

    private Element createDanceNumberElement(DanceNumber danceNumber, Document document){
        var danceElement = document.createElement(DanceXmlTags.DANCENUMBER.getValue());
        //attributes
        danceElement.setAttribute(DanceXmlTags.ID.getValue(), danceNumber.getId());
        //type
        var typeElement = document.createElement(DanceXmlTags.TYPE.getValue());
        typeElement.appendChild(document.createTextNode(danceNumber.getType()));
        danceElement.appendChild(typeElement);
        //scene
        var sceneElement = document.createElement(DanceXmlTags.SCENE.getValue());
        sceneElement.appendChild(document.createTextNode(danceNumber.getScene()));
        danceElement.appendChild(sceneElement);
        //numberOfDancers
        var numberOfDancersElement = document.createElement(DanceXmlTags.NUMBEROFDANCERS.getValue());
        numberOfDancersElement.appendChild(document.createTextNode(danceNumber.getNumberOfDancers()));
        danceElement.appendChild(numberOfDancersElement);
        //music
        var musicElement = document.createElement(DanceXmlTags.MUSIC.getValue());
        musicElement.appendChild(document.createTextNode(danceNumber.getMusic()));
        danceElement.appendChild(musicElement);
        //visualParameters
        danceElement.appendChild(createDancersElement(danceNumber.getDancers(), document));

        //number
        var numberElement = document.createElement(DanceXmlTags.NUMBER.getValue());
        numberElement.appendChild(document.createTextNode(Integer.toString(danceNumber.getNumber())));
        danceElement.appendChild(numberElement);
        return danceElement;
    }


    private Element createDancersElement(Dancers dancers, Document document){
        var dancersElement = document.createElement(DanceXmlTags.DANCERS.getValue());
        //name
        var nameElement = document.createElement(DanceXmlTags.NAME.getValue());
        nameElement.appendChild(document.createTextNode(dancers.getName()));
        dancersElement.appendChild(nameElement);
        //age
        var ageElement = document.createElement(DanceXmlTags.AGE.getValue());
        ageElement.appendChild(document.createTextNode(Integer.toString(dancers.getAge())));
        dancersElement.appendChild(ageElement);
        //origin
        var originElement = document.createElement(DanceXmlTags.ORIGIN.getValue());
        originElement.appendChild(document.createTextNode(dancers.getOrigin()));
        dancersElement.appendChild(originElement);

        return dancersElement;
    }
}

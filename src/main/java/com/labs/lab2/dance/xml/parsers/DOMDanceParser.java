package com.labs.lab2.dance.xml.parsers;

import com.labs.lab2.dance.DanceNumber;
import com.labs.lab2.dance.DanceXmlTags;
import com.labs.lab2.dance.Dancers;
import com.labs.lab2.dance.Dances;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import java.io.IOException;

/**
 * Dance XML file DOM Parser
 * @author Alex
 */
public class DOMDanceParser extends DanceParser {
    private DocumentBuilder docBuilder;

    public DOMDanceParser(String schemaPath) {
        super(schemaPath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Dances readDancesFromXMLFile(String filePath){
        try {
            danceValidator.validate(filePath);
            if(danceHandler.getErrors().size() == 0){
                Dances dances = new Dances();
                Document doc = docBuilder.parse(filePath);
                Element root = doc.getDocumentElement();
                NodeList dancesList = root.getElementsByTagName(DanceXmlTags.DANCENUMBER.getValue());
                for (int i = 0; i < dancesList.getLength(); i++) {
                    Element danceElement = (Element) dancesList.item(i);
                    DanceNumber danceNumber = buildDanceNumber(danceElement);
                    dances.add(danceNumber);
                }
                return dances;
            }
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        return null;
    }
    private DanceNumber buildDanceNumber(Element danceNumberElement) {
        DanceNumber danceNumber = new DanceNumber();
        //id
        danceNumber.setId(danceNumberElement.getAttribute(DanceXmlTags.ID.getValue()));
        //type
        danceNumber.setType(getElementTextContent(danceNumberElement, DanceXmlTags.TYPE.getValue()));
        //scene
        danceNumber.setScene(getElementTextContent(danceNumberElement, DanceXmlTags.SCENE.getValue()));
        //numberOfDancers
        danceNumber.setNumberOfDancers(getElementTextContent(danceNumberElement, DanceXmlTags.NUMBEROFDANCERS.getValue()));
        //music
        danceNumber.setMusic(getElementTextContent(danceNumberElement, DanceXmlTags.MUSIC.getValue()));
        //dancers
        var dancers = new Dancers();
        var dancersElement = (Element)danceNumberElement.getElementsByTagName(DanceXmlTags.DANCERS.getValue()).item(0);
        buildDancers(dancers, dancersElement);
        danceNumber.setDancers(dancers);
        //number
        danceNumber.setNumber(Integer.parseInt(getElementTextContent(danceNumberElement, DanceXmlTags.NUMBER.getValue())));

        return danceNumber;
    }
    private void buildDancers(Dancers dancers, Element dancersElement) {
        //name
        dancers.setName(getElementTextContent(dancersElement, DanceXmlTags.NAME.getValue()));
        //age
        dancers.setAge(Integer.parseInt(getElementTextContent(dancersElement, DanceXmlTags.AGE.getValue())));
        //origin
        dancers.setOrigin(getElementTextContent(dancersElement, DanceXmlTags.ORIGIN.getValue()));

    }
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}

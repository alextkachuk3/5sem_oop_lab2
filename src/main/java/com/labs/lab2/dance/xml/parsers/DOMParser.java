package com.labs.lab2.dance.xml.parsers;

import com.labs.lab2.dance.Dances;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DOMParser extends Parser {
    /**
     * Reads dance numbers from XML file
     * @param xml - XML file
     * @return dance numbers from file
     */
    public Dances parse(File xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(xml);
            document.getDocumentElement().normalize();
            Element rootNode = document.getDocumentElement();
            DanceHandler danceHandler = new DanceHandler();
            NodeList danceNodesList = rootNode.getElementsByTagName(danceHandler.getName());
            for (int danceNode = 0; danceNode < danceNodesList.getLength(); danceNode++) {
                Element danceElement = (Element) danceNodesList.item(danceNode);
                parseNodes(danceElement, danceHandler);
            }
            return new Dances(danceHandler.getListOfDanceNumbers());
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    private void parseNodes(Node node, DanceHandler danceHandler) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Map<String, String> attributes = new HashMap<>();
            if (node.getAttributes() != null) {
                for (int i = 0; i < node.getAttributes().getLength(); i++) {
                    attributes.put(node.getAttributes().item(i).getNodeName(),
                            node.getAttributes().item(i).getTextContent());
                }
            }
            danceHandler.setField(node.getNodeName(), node.getTextContent(), attributes);
            if (node.getChildNodes() != null) {
                for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                    parseNodes(node.getChildNodes().item(i), danceHandler);
                }
            }
        }
    }
}

package com.labs.lab2.test;

import com.labs.lab2.dance.DanceHandler;
import com.labs.lab2.dance.DanceValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;

import static org.junit.Assert.*;

public class DanceHandlerTest {
    private static DanceHandler handler;
    private static DanceValidator validator;
    private static String schemaPath="Dance.xsd";
    private static String incorrectXml = "src/main/java/com/labs/lab2/test/data/dance_incorrect.xml";
    private static String correctXml = "src/main/java/com/labs/lab2/test/data/dance_correct.xml";
    @Before
    public void setUp() {
        handler = new DanceHandler();
        try {
            validator = new DanceValidator(schemaPath, handler);
        } catch (SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        handler = null;
        validator = null;
    }

    @Test
    public void getErrors() {
        assertEquals(0, handler.getErrors().size());//before validation there isn't errors
        try {
            validator.validate(incorrectXml);
            assertNotEquals(0,
                    handler.getErrors().size());//after validation of incorrect xml file there are some errors
            validator.validate(correctXml);
            assertEquals(0, handler.getErrors().size());//after validation of correct xml file errors is empty
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}
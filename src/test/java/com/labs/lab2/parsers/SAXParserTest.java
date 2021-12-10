package com.labs.lab2.parsers;

import com.labs.lab2.dance.xml.parsers.DOMParser;
import com.labs.lab2.dance.xml.parsers.SAXParser;
import org.junit.jupiter.api.BeforeEach;

public class SAXParserTest extends DanceXmlTest {
    @BeforeEach
    public void setUp() {
        parser = new SAXParser();
    }
}

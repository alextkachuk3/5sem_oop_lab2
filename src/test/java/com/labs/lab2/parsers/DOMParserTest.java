package com.labs.lab2.parsers;

import com.labs.lab2.dance.xml.parsers.DOMParser;
import org.junit.jupiter.api.BeforeEach;

public class DOMParserTest extends DanceXmlTest {
    @BeforeEach
    public void setUp() {
        parser = new DOMParser();
    }
}

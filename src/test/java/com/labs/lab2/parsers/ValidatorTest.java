package com.labs.lab2.parsers;

import com.labs.lab2.dance.xml.parsers.SchemaValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    @Test
    public void validateCorrectFile() {
        assertTrue(SchemaValidator.validate("Dance.xml", "Dance.xsd"));
    }

}

package com.labs.lab2.dance.xml.writers;

import com.labs.lab2.dance.Dances;
import com.labs.lab2.dance.xml.DanceXML;

public abstract class DanceWriter extends DanceXML {

    public DanceWriter(String schemaPath) {
        super(schemaPath);
    }

    /**
     * Writes dance numbers to XML file
     * @param dances - dances for write
     * @param filePath - path to XML file
     * @return true if success, else false
     */
    public abstract boolean writeDancesToXMLFile(Dances dances, String filePath);
}

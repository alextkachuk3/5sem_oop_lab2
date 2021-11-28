package com.labs.lab2.dance;

public enum DanceXmlTags {
    DANCE("Dance"),
    DANCE_NUMBER("danceNumber"),
    ID("id"),
    TYPE("type"),
    SCENE("scene"),
    NUMBER_OF_DANCERS("numberOfDancers"),
    MUSIC("music"),
    NAME("name"),
    AGE("age"),
    ORIGIN("origin"),
    DANCERS("dancers"),
    NUMBER("number");

    private final String value;

    DanceXmlTags(String _value) {
        value = _value;
    }

    public String getValue() {
        return value;
    }
}

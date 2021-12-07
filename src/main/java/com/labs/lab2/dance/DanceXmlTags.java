package com.labs.lab2.dance;

public enum DanceXmlTags {
    DANCE("Dance"),
    DANCENUMBER("danceNumber"),
    ID("id"),
    TYPE("type"),
    SCENE("scene"),
    NUMBEROFDANCERS("numberOfDancers"),
    MUSIC("music"),
    NAME("name"),
    AGE("age"),
    ORIGIN("origin"),
    NUMBER("number"),
    DANCERS("dancers");

    private final String value;

    DanceXmlTags(String _value) {
        value = _value;
    }

    public String getValue() {
        return value;
    }
}

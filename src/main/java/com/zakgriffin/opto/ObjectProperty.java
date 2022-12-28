package com.zakgriffin.opto;

import javafx.beans.property.Property;

public class ObjectProperty {
    Property<O> property;
    public String name;

    public ObjectProperty(Property<O> property, String name) {
        this.property = property;
        this.name = name;
    }
}
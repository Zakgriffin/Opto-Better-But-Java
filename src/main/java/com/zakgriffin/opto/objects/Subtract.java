package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.O;
import com.zakgriffin.opto.ObjectProperty;
import com.zakgriffin.opto.ObjectType;
import javafx.beans.property.Property;

public class Subtract implements O {
    Property<O> minuendRegister;
    Property<O> subtrahendRegister;

    @Override
    public ObjectType getType() {
        return ObjectType.SUBTRACT;
    }

    @Override
    public ObjectProperty[] properties() {
        return new ObjectProperty[] {
                new ObjectProperty(minuendRegister, "minuend_register"),
                new ObjectProperty(subtrahendRegister, "subtrahend_register")
        };
    }
}

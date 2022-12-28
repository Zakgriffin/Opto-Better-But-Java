package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.O;
import com.zakgriffin.opto.ObjectProperty;
import com.zakgriffin.opto.ObjectType;
import javafx.beans.property.Property;

public class Add implements O {
    Property<O> addendRegister;
    Property<O> augendRegister;

    @Override
    public ObjectType getType() {
        return ObjectType.ADD;
    }

    @Override
    public ObjectProperty[] properties() {
        return new ObjectProperty[] {
                new ObjectProperty(addendRegister, "addend_register"),
                new ObjectProperty(augendRegister, "augend_register")
        };
    }
}
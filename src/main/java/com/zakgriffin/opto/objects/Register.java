package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.O;
import com.zakgriffin.opto.ObjectProperty;
import com.zakgriffin.opto.ObjectType;
import javafx.beans.property.Property;

public class Register implements O {
    Property<O> number;

    @Override
    public ObjectType getType() {
        return ObjectType.REGISTER;
    }

    public ObjectProperty[] properties() {
        return new ObjectProperty[]{
                new ObjectProperty(number, "number")
        };
    }
}

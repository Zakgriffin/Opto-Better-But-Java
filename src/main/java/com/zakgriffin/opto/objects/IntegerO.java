package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.O;
import com.zakgriffin.opto.ObjectProperty;
import com.zakgriffin.opto.ObjectType;
import javafx.beans.property.Property;

public class IntegerO implements O {
    Property<Integer> integer;

    @Override
    public ObjectType getType() {
        return ObjectType.INTEGER;
    }

    @Override
    public ObjectProperty[] properties() {
        return new ObjectProperty[0];
    }
}

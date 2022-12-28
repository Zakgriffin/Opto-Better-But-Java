package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.*;
import javafx.beans.property.Property;
import javafx.scene.Node;

public class Subtract implements O, DefaultViewO {
    Property<O> minuendRegister;
    Property<O> subtrahendRegister;

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return NormalView.defaultNormalView(this, owningLookupBox);
    }

    @Override
    public ObjectProperty[] properties() {
        return new ObjectProperty[] {
                new ObjectProperty(minuendRegister, "minuend_register"),
                new ObjectProperty(subtrahendRegister, "subtrahend_register")
        };
    }
}

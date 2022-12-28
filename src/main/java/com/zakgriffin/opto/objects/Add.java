package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.*;
import javafx.beans.property.Property;
import javafx.scene.Node;

public class Add implements O, DefaultViewO {
    Property<O> addendRegister;
    Property<O> augendRegister;

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return NormalView.defaultNormalView(this, owningLookupBox);
    }

    @Override
    public ObjectProperty[] properties() {
        return new ObjectProperty[] {
                new ObjectProperty(addendRegister, "addend_register"),
                new ObjectProperty(augendRegister, "augend_register")
        };
    }
}
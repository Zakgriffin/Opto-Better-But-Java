package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.*;
import javafx.beans.property.Property;
import javafx.scene.Node;

public class Register implements O, DefaultViewO {
    Property<O> number;

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return NormalView.defaultNormalView(this, owningLookupBox);
    }

    public ObjectProperty[] properties() {
        return new ObjectProperty[]{
                new ObjectProperty(number, "number")
        };
    }
}

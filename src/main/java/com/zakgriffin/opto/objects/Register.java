package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.*;
import javafx.beans.property.Property;
import javafx.scene.Node;

public class Register implements O, DefaultViewO {
    ObservableO number = new ObservableO();

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return NormalView.defaultNormalView(this, owningLookupBox);
    }

    public NamedObservableO[] namedObservableOs() {
        return new NamedObservableO[]{
                new NamedObservableO(number, "number")
        };
    }
}

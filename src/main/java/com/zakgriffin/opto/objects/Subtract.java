package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.*;
import javafx.beans.property.Property;
import javafx.scene.Node;

public class Subtract implements O, DefaultViewO {
    ObservableO minuendRegister = new ObservableO();
    ObservableO subtrahendRegister = new ObservableO();

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return NormalView.defaultNormalView(this, owningLookupBox);
    }

    @Override
    public NamedObservableO[] namedObservableOs() {
        return new NamedObservableO[] {
                new NamedObservableO(minuendRegister, "minuend_register"),
                new NamedObservableO(subtrahendRegister, "subtrahend_register")
        };
    }
}

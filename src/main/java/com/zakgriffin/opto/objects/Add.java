package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.*;
import javafx.scene.Node;

public class Add implements O, DefaultViewO {
    ObservableO addendRegister = new ObservableO();
    ObservableO augendRegister = new ObservableO();

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return NormalView.defaultNormalView(this, owningLookupBox);
    }

    @Override
    public NamedObservableO[] namedObservableOs() {
        return new NamedObservableO[] {
                new NamedObservableO(addendRegister, "addend_register"),
                new NamedObservableO(augendRegister, "augend_register")
        };
    }
}
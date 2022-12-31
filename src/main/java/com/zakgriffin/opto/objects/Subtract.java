package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.*;
import com.zakgriffin.opto.types.SetType;
import javafx.scene.Node;

import java.util.Set;

public class Subtract implements O, DefaultViewO {
    Observable<O> minuendRegister = new Observable<>();
    Observable<O> subtrahendRegister = new Observable<>();

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return NormalView.defaultNormalView(this, owningLookupBox);
    }

    @Override
    public NamedObservableO[] namedObservableOs() {
        return new NamedObservableO[] {
                new NamedObservableO(minuendRegister, "minuend_register", new SetType(Set.of(Register.class))),
                new NamedObservableO(subtrahendRegister, "subtrahend_register", new SetType(Set.of(Register.class)))
        };
    }
}

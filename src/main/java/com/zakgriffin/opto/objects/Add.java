package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.*;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.types.SetType;
import com.zakgriffin.opto.views.DefaultViewO;
import com.zakgriffin.opto.views.Views;
import javafx.scene.Node;

import java.util.Set;

public class Add implements O, DefaultViewO {
    Observable<O> addendRegister = new Observable<>();
    Observable<O> augendRegister = new Observable<>();

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return Views.defaultNormalView(this, owningLookupBox);
    }

    @Override
    public NamedObservableO[] namedObservableOs() {
        return new NamedObservableO[] {
                new NamedObservableO(addendRegister, "addend_register", new SetType(Set.of(Register.class))),
                new NamedObservableO(augendRegister, "augend_register", new SetType(Set.of(Register.class)))
        };
    }
}
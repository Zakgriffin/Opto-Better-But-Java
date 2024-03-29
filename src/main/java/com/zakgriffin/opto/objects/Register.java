package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.*;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.types.SetType;
import com.zakgriffin.opto.views.DefaultViewO;
import com.zakgriffin.opto.views.Views;
import javafx.scene.Node;

import java.util.Set;

public class Register implements O, DefaultViewO {
    Observable<O> number = new Observable<>();

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return Views.horizontalView(this, owningLookupBox);
    }

    public NamedObservableO[] namedObservableOs() {
        return new NamedObservableO[]{
                new NamedObservableO(number, "number", new SetType(Set.of(IntegerO.class)))
        };
    }
}

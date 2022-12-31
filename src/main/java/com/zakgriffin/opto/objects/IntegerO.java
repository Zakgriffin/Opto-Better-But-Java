package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.*;
import javafx.scene.Node;

public class IntegerO implements O, DefaultViewO {
    Observable<O> integer = new Observable<>();

    public IntegerO(int i) {

    }

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return NormalView.defaultNormalView(this, owningLookupBox);
    }

    @Override
    public NamedObservableO[] namedObservableOs() {
        return new NamedObservableO[]{};
    }
}

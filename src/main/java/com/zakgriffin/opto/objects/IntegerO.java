package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.*;
import com.zakgriffin.opto.view.DefaultViewO;
import com.zakgriffin.opto.view.Views;
import javafx.scene.Node;

public class IntegerO implements O, DefaultViewO {
    public int integer;

    public IntegerO(int integer) {
        this.integer = integer;
    }

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return Views.defaultNormalView(this, owningLookupBox);
    }

    @Override
    public NamedObservableO[] namedObservableOs() {
        return new NamedObservableO[]{};
    }
}

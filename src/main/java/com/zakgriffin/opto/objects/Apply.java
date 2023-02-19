package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.LookupBox;
import com.zakgriffin.opto.NamedObservableO;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.types.AnyType;
import com.zakgriffin.opto.views.DefaultViewO;
import com.zakgriffin.opto.views.Views;
import javafx.scene.Node;

public class Apply implements O, DefaultViewO {
    Observable<O> function = new Observable<>();
    Observable<O> input = new Observable<>();

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return Views.horizontalView(this, owningLookupBox);
    }

    @Override
    public NamedObservableO[] namedObservableOs() {
        return new NamedObservableO[] {
                new NamedObservableO(function, "function", new AnyType()),
                new NamedObservableO(input, "input", new AnyType()),
        };
    }
}

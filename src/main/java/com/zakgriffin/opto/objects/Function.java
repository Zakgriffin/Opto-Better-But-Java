package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.LookupBox;
import com.zakgriffin.opto.NamedObservableO;
import com.zakgriffin.opto.reactivity.Binding;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.types.AnyType;
import com.zakgriffin.opto.views.DefaultViewO;
import com.zakgriffin.opto.views.Views;
import javafx.scene.Node;


public class Function implements O, DefaultViewO {
    Observable<O> input = new Observable<>();
    Observable<O> body = new Observable<>();

    public Function() {
        Binding binding = new Binding();
        Binding.addBinding(input, binding);
    }

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return Views.verticalView(this, owningLookupBox);
    }

    @Override
    public NamedObservableO[] namedObservableOs() {
        return new NamedObservableO[] {
                new NamedObservableO(input, "input", new AnyType()),
                new NamedObservableO(body, "body", new AnyType()),
        };
    }
}

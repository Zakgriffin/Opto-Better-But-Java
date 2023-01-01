package com.zakgriffin.opto.objects.math;

import com.zakgriffin.opto.*;
import com.zakgriffin.opto.objects.O;
import com.zakgriffin.opto.types.MathExpressionType;
import com.zakgriffin.opto.view.InfixViewO;
import com.zakgriffin.opto.view.Views;
import javafx.scene.Node;

public class Add implements O, InfixViewO {
    Observable<O> addend = new Observable<>();
    Observable<O> augend = new Observable<>();

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return Views.infixView(this, owningLookupBox);
    }

    @Override
    public NamedObservableO left() {
        return new NamedObservableO(addend, "addend", new MathExpressionType());
    }

    @Override
    public NamedObservableO right() {
        return new NamedObservableO(augend, "augend", new MathExpressionType());
    }
}

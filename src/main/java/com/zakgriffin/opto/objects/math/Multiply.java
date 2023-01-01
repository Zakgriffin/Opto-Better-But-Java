package com.zakgriffin.opto.objects.math;

import com.zakgriffin.opto.*;
import com.zakgriffin.opto.objects.O;
import com.zakgriffin.opto.types.MathExpressionType;
import com.zakgriffin.opto.view.InfixViewO;
import com.zakgriffin.opto.view.Views;
import javafx.scene.Node;

public class Multiply implements O, InfixViewO {
    Observable<O> multiplier = new Observable<>();
    Observable<O> multiplicand = new Observable<>();

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return Views.infixView(this, owningLookupBox);
    }

    @Override
    public NamedObservableO left() {
        return new NamedObservableO(multiplier, "multiplier", new MathExpressionType());
    }

    @Override
    public NamedObservableO right() {
        return new NamedObservableO(multiplicand, "multiplicand", new MathExpressionType());
    }
}
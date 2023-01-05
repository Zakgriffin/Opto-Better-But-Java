package com.zakgriffin.opto.objects.math;

import com.zakgriffin.opto.*;
import com.zakgriffin.opto.objects.IntegerO;
import com.zakgriffin.opto.objects.O;
import com.zakgriffin.opto.types.MathExpressionType;
import com.zakgriffin.opto.view.InfixViewO;
import com.zakgriffin.opto.view.Views;
import javafx.scene.Node;

public class Subtract implements O, InfixViewO, MathExpression {
    Observable<O> minuend = new Observable<>();
    Observable<O> subtrahend = new Observable<>();

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return Views.infixView(this, owningLookupBox);
    }

    @Override
    public NamedObservableO left() {
        return new NamedObservableO(minuend, "minuend", new MathExpressionType());
    }

    @Override
    public NamedObservableO right() {
        return new NamedObservableO(subtrahend, "subtrahend", new MathExpressionType());
    }

    Observable<IntegerO> evaluatedO = MathExpression.evaluatedHelper(minuend, subtrahend, (a, b) -> a - b);

    @Override
    public Observable<IntegerO> evaluated() {
        return evaluatedO;
    }
}
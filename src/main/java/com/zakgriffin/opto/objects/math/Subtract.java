package com.zakgriffin.opto.objects.math;

import com.zakgriffin.opto.*;
import com.zakgriffin.opto.objects.O;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.reactivity.Signal;
import com.zakgriffin.opto.reactivity.reversible.ReversibleContext;
import com.zakgriffin.opto.types.MathExpressionType;
import com.zakgriffin.opto.views.InfixViewO;
import com.zakgriffin.opto.views.Views;
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

    @Override
    public Signal setupEvaluated(ReversibleContext oRev) {
        return MathExpression.setupEvaluatedHelper(this, oRev, minuend, subtrahend, (a, b) -> a - b);
    }
}
package com.zakgriffin.opto.objects.math;

import com.zakgriffin.opto.*;
import com.zakgriffin.opto.objects.O;
import com.zakgriffin.opto.reactivity.Tracker;
import com.zakgriffin.opto.types.MathExpressionType;
import com.zakgriffin.opto.views.InfixViewO;
import com.zakgriffin.opto.views.Views;
import javafx.scene.Node;

public class Subtract implements O, InfixViewO, MathExpression {
    Tracker<O> minuend = new Tracker<>();
    Tracker<O> subtrahend = new Tracker<>();

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

    Tracker<Integer> evaluated = MathExpression.evaluatedHelper(minuend, subtrahend, (a, b) -> a - b);
    @Override
    public Tracker<Integer> evaluate() {
        return evaluated;
    }
}
package com.zakgriffin.opto.objects.math;

import com.zakgriffin.opto.*;
import com.zakgriffin.opto.objects.O;
import com.zakgriffin.opto.reactivity.Tracker;
import com.zakgriffin.opto.types.MathExpressionType;
import com.zakgriffin.opto.views.InfixViewO;
import com.zakgriffin.opto.views.Views;
import javafx.scene.Node;

public class Multiply implements O, InfixViewO, MathExpression {
    Tracker<O> multiplier = new Tracker<>();
    Tracker<O> multiplicand = new Tracker<>();

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

    Tracker<Integer> evaluated = MathExpression.evaluatedHelper(multiplier, multiplicand, (a, b) -> a * b);
    @Override
    public Tracker<Integer> evaluate() {
        return evaluated;
    }
}
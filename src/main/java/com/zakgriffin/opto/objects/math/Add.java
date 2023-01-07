package com.zakgriffin.opto.objects.math;

import com.zakgriffin.opto.*;
import com.zakgriffin.opto.objects.O;
import com.zakgriffin.opto.reactivity.Tracker;
import com.zakgriffin.opto.types.MathExpressionType;
import com.zakgriffin.opto.views.InfixViewO;
import com.zakgriffin.opto.views.Views;
import javafx.scene.Node;

public class Add implements O, InfixViewO, MathExpression {
    Tracker<O> addend = new Tracker<>();
    Tracker<O> augend = new Tracker<>();

    Binding<Integer> evaluated = new Tracker<>();

    public Add() {
        addend.addListener(bongo((valye) -> {
            connect(addend.signal, n.evaluated.signal);
        }));
    }

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

    Tracker<Integer> evaluated = MathExpression.evaluatedHelper(addend, augend, Integer::sum);


    @Override
    public Tracker<Integer> evaluate() {
        return addend.get() + augend.get();
    }
}

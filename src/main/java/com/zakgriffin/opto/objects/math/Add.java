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

public class Add implements O, InfixViewO, MathExpression {
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

    @Override
    public Signal setupEvaluated(ReversibleContext oRev) {
        return MathExpression.setupEvaluatedHelper(this, oRev, addend, augend, Integer::sum);
    }

    @Override
    public String toString() {
        return "(" + (addend.get() == null ? null : addend.get()) + " + " + (augend.get() == null ? null : augend.get()) + ")";
    }
}

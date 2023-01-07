package com.zakgriffin.opto.objects.math;

import com.zakgriffin.opto.*;
import com.zakgriffin.opto.objects.IntegerO;
import com.zakgriffin.opto.objects.O;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.types.MathExpressionType;
import com.zakgriffin.opto.views.InfixViewO;
import com.zakgriffin.opto.views.Views;
import javafx.scene.Node;

public class Multiply implements O, InfixViewO, MathExpression {
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

    Observable<IntegerO> evaluatedO = MathExpression.evaluatedHelper(multiplier, multiplicand, (a, b) -> a * b);

    @Override
    public Observable<IntegerO> evaluated() {
        return evaluatedO;
    }
}
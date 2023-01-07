package com.zakgriffin.opto.objects.math;

import com.zakgriffin.opto.*;
import com.zakgriffin.opto.objects.IntegerO;
import com.zakgriffin.opto.objects.O;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.types.*;
import com.zakgriffin.opto.views.InfixViewO;
import com.zakgriffin.opto.views.Views;
import javafx.scene.Node;

import java.util.Set;

public class Divide implements O, InfixViewO, MathExpression {
    Observable<O> dividend = new Observable<>();
    Observable<O> divisor = new Observable<>();

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return Views.infixView(this, owningLookupBox);
    }

    @Override
    public NamedObservableO left() {
        return new NamedObservableO(dividend, "dividend", new MathExpressionType());
    }

    @Override
    public NamedObservableO right() {
        return new NamedObservableO(divisor, "divisor", new DifferenceType(
                new MathExpressionType(),
                new IntegerSet(Set.of(0))
        ));
    }

    Observable<IntegerO> evaluatedO = MathExpression.evaluatedHelper(dividend, divisor, (a, b) -> a / b);

    @Override
    public Observable<IntegerO> evaluated() {
        return evaluatedO;
    }
}
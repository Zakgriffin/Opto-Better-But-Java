package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.*;
import com.zakgriffin.opto.objects.math.MathExpression;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.reactivity.Signal;
import com.zakgriffin.opto.reactivity.reversible.ReversibleContext;
import com.zakgriffin.opto.views.DefaultViewO;
import com.zakgriffin.opto.views.Views;
import javafx.scene.Node;

import static com.zakgriffin.opto.objects.math.MathExpressionEvaluated.expressionToEvaluated;

public class IntegerO implements O, DefaultViewO, MathExpression {
    public final int i;

    public IntegerO (int value){
        this.i = value;
    }

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return Views.defaultNormalView(this, owningLookupBox);
    }

    @Override
    public NamedObservableO[] namedObservableOs() {
        return new NamedObservableO[]{};
    }

    @Override
    public Observable<Integer> giveEvaluated(Signal parentSignal, ReversibleContext oRev) {
        Observable<Integer> evaluated = expressionToEvaluated.computeIfAbsent(this, ($) -> new Observable<>());
        evaluated.set(i);
        return evaluated;
    }
}

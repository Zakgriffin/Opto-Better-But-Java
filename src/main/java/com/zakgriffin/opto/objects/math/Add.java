package com.zakgriffin.opto.objects.math;

import com.zakgriffin.opto.*;
import com.zakgriffin.opto.objects.IntegerO;
import com.zakgriffin.opto.objects.O;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.types.MathExpressionType;
import com.zakgriffin.opto.view.InfixViewO;
import com.zakgriffin.opto.view.Views;
import javafx.scene.Node;

public class Add implements O, InfixViewO, MathExpression {
    O addend;
    O augend;

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

    Observable<IntegerO> evaluatedO = MathExpression.evaluatedHelper(addend, augend, Integer::sum);
    @Override
    public Observable<IntegerO> evaluated() {
        return evaluatedO;
    }
}

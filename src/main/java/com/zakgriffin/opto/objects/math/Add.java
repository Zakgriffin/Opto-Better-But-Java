package com.zakgriffin.opto.objects.math;

import com.zakgriffin.opto.*;
import com.zakgriffin.opto.objects.O;
import com.zakgriffin.opto.types.MathExpressionType;
import com.zakgriffin.opto.views.InfixViewO;
import com.zakgriffin.opto.views.Views;
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
        return new NamedObservableO((newAdded) -> addend = newAdded, "addend", new MathExpressionType());
    }

    @Override
    public NamedObservableO right() {
        return new NamedObservableO(augend, "augend", new MathExpressionType());
    }
}

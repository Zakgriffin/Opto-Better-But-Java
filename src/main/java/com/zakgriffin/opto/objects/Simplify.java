package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.reactivity.Binding;
import com.zakgriffin.opto.LookupBox;
import com.zakgriffin.opto.NamedObservableO;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.objects.math.MathExpression;
import com.zakgriffin.opto.types.MathExpressionType;
import com.zakgriffin.opto.view.DefaultViewO;
import com.zakgriffin.opto.view.Views;
import javafx.scene.Node;

import java.util.List;

public class Simplify implements O, DefaultViewO {
    O expression;
    O result;

    public Simplify() {

    }

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return Views.verticalView(this, owningLookupBox);
    }

    @Override
    public NamedObservableO[] namedObservableOs() {
        return new NamedObservableO[]{
                new NamedObservableO(expression, "expression", new MathExpressionType()),
                new NamedObservableO(result, "result", (o) -> {
//                    if(o instanceof IntegerO io) {
//                        return true;
//                        return io.integer == MathExpression.simplify(expression.get());
//                    }
                    return true;
                })
        };
    }
}

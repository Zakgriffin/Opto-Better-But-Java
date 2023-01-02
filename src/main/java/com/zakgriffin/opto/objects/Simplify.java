package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.LookupBox;
import com.zakgriffin.opto.NamedObservableO;
import com.zakgriffin.opto.Observable;
import com.zakgriffin.opto.types.MathExpressionType;
import com.zakgriffin.opto.view.DefaultViewO;
import com.zakgriffin.opto.view.Views;
import javafx.scene.Node;

public class Simplify implements O, DefaultViewO {
    Observable<O> expression = new Observable<>();
    Observable<O> result = new Observable<>();

    public Simplify() {
//        result.bind(expression.evaluate());
    }

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return Views.verticalView(this, owningLookupBox);
    }

    @Override
    public NamedObservableO[] namedObservableOs() {
        return new NamedObservableO[] {
                new NamedObservableO(expression, "expression", new MathExpressionType()),
                new NamedObservableO(result, "result", (o) -> {
//                    if(o instanceof IntegerO io) {
//                        return true;
//                        return io.integer == MathExpression.simplify(expression.get());
//                    }
                    return false;
                })
        };
    }
}
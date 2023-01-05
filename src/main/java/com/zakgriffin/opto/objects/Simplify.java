package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.Binding;
import com.zakgriffin.opto.LookupBox;
import com.zakgriffin.opto.NamedObservableO;
import com.zakgriffin.opto.Observable;
import com.zakgriffin.opto.objects.math.MathExpression;
import com.zakgriffin.opto.types.MathExpressionType;
import com.zakgriffin.opto.view.DefaultViewO;
import com.zakgriffin.opto.view.Views;
import javafx.scene.Node;

import java.util.List;

public class Simplify implements O, DefaultViewO {
    Observable<O> expression = new Observable<>();
    Observable<O> result = new Observable<>();

    public Simplify() {
        Observable<Observable<IntegerO>> expressionEvaluated = new Observable<>();
        Binding.createBinding(expressionEvaluated, () -> {
            if (expression.get() instanceof MathExpression e) {
                System.out.println("ooooo");
                return e.evaluated();
            }
            return null;
        }, List.of(expression));

        expressionEvaluated.addListener((a, d, value) -> {
            if(value == null) return;
            if (value.get() != null) {
                System.out.println("omp_once: " + value.get().i);
            }
            value.addListener((k, j, v) -> {
                if (v != null) {
                    System.out.println("omp: " + v.i);
                }
            });
        });
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

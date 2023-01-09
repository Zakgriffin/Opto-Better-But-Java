package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.LookupBox;
import com.zakgriffin.opto.NamedObservableO;
import com.zakgriffin.opto.objects.math.MathExpression;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.reactivity.Signal;
import com.zakgriffin.opto.types.MathExpressionType;
import com.zakgriffin.opto.views.DefaultViewO;
import com.zakgriffin.opto.views.Views;
import javafx.scene.Node;

import static com.zakgriffin.opto.objects.math.MathExpressionEvaluated.expressionToEvaluated;
import static com.zakgriffin.opto.reactivity.reversible.ReversibleListener.reversible;


public class Simplify implements O, DefaultViewO {
    Observable<O> expression = new Observable<>();
    Observable<O> result = new Observable<>();

    public Simplify() {
        expression.addListenerAndRunNow(reversible((expr, rev) -> {
            if (expr instanceof MathExpression exprMath) {
                Signal poo = new Signal(() -> {
                    System.out.println("ooo: " + expressionToEvaluated.get(exprMath).get());
                });
                exprMath.giveEvaluated(poo, rev);
            }
        }));
    }

//    Circle bonk(HBox treeContainer, O expr) {
//        expressionCircle = new Circle(expr);
//        treeContainer.addChild(expressionCircle);
//
//        expression.changeLeft((left) -> {
//            leftCircle = new Circle(left);
//            treeContainer.addChild(leftCircle);
//            leftCircle.x.bind(expressionCircle.x - 30);
//            leftCircle.y.bind(expressionCircle.y + 20);
//            bonk(left);
//        });
//
//        expression.changeRight((right) -> {
//            rightCircle = new Circle(right);
//            treeContainer.addChild(rightCircle);
//            rightCircle.x.bind(expressionCircle.x + 30);
//            rightCircle.y.bind(expressionCircle.y + 20);
//            bonk(right);
//        });
//    }

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return Views.verticalView(this, owningLookupBox);
    }

    @Override
    public NamedObservableO[] namedObservableOs() {
        return new NamedObservableO[]{
                new NamedObservableO(expression, "expression", new MathExpressionType()),
                new NamedObservableO(result, "result", (o) -> true)
        };
    }
}

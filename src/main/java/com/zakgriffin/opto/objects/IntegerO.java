package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.*;
import com.zakgriffin.opto.objects.math.MathExpression;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.reactivity.Signal;
import com.zakgriffin.opto.reactivity.reversible.ReversibleContext;
import com.zakgriffin.opto.views.DefaultViewO;
import com.zakgriffin.opto.views.Views;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import static com.zakgriffin.opto.objects.math.MathExpressionEvaluated.expressionToEvaluated;

public class IntegerO implements O, MathExpression {
    public final int i;

    public IntegerO(int value) {
        this.i = value;
    }

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        TextField textField = owningLookupBox.getTextField();
        textField.setText(i + "");
        return textField;
    }


    @Override
    public Signal setupEvaluated(ReversibleContext oRev) {
        Observable<Integer> evaluated = expressionToEvaluated.computeIfAbsent(this, ($) -> {
            Observable<Integer> e = new Observable<>();
            e.set(i);
            return e;
        });
        Signal signal = new Signal(() -> {
        });
        Platform.runLater(() -> signal.trigger());
        return signal;
    }

    @Override
    public String toString() {
        return "" + i;
    }
}

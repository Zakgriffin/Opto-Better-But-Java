package com.zakgriffin.opto.objects.math;

import com.zakgriffin.opto.objects.O;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.reactivity.Signal;
import com.zakgriffin.opto.reactivity.reversible.ReversibleContext;
import javafx.application.Platform;

import java.util.function.BiFunction;

import static com.zakgriffin.opto.objects.math.MathExpressionEvaluated.expressionToEvaluated;
import static com.zakgriffin.opto.reactivity.reversible.ReversibleListener.reversible;

public interface MathExpression {
    Signal setupEvaluated(ReversibleContext oRev);

    static Signal setupEvaluatedHelper(MathExpression mathExpression, ReversibleContext oRev, Observable<O> left, Observable<O> right, BiFunction<Integer, Integer, Integer> f) {
        Observable<Integer> evaluated = expressionToEvaluated.computeIfAbsent(mathExpression, ($) -> new Observable<>());

        Signal recalcSignal = new Signal(() -> {
            if (!(left.get() instanceof MathExpression leftMath) || !(right.get() instanceof MathExpression rightMath)) {
                evaluated.set(null);
                return;
            }
            Observable<Integer> leftEvaluated = expressionToEvaluated.get(leftMath);
            Observable<Integer> rightEvaluated = expressionToEvaluated.get(rightMath);
            if(leftEvaluated.get() == null || rightEvaluated.get() == null) {
                evaluated.set(null);
                return;
            }
            evaluated.set(f.apply(leftEvaluated.get(), rightEvaluated.get()));
        });


        oRev.useListenerAndRunNow(left, reversible((_left, leftRev) -> {
            if (_left instanceof MathExpression leftMathExpression) {
                Signal leftSignal = leftMathExpression.setupEvaluated(leftRev);
                leftRev.useSignalConnection(leftSignal, recalcSignal);
            }
        }));
        oRev.useListenerAndRunNow(right, reversible((_right, rightRev) -> {
            if (_right instanceof MathExpression rightMathExpression) {
                Signal rightSignal = rightMathExpression.setupEvaluated(rightRev);
                rightRev.useSignalConnection(rightSignal, recalcSignal);
            }
        }));
        Platform.runLater(() -> recalcSignal.trigger());

        return recalcSignal;
    }
}

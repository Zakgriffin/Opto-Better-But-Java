package com.zakgriffin.opto.objects.math;

import com.zakgriffin.opto.objects.O;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.reactivity.Signal;
import com.zakgriffin.opto.reactivity.reversible.ReversibleContext;

import java.util.function.BiFunction;

import static com.zakgriffin.opto.objects.math.MathExpressionEvaluated.expressionToEvaluated;
import static com.zakgriffin.opto.reactivity.reversible.ReversibleListener.reversible;

public interface MathExpression {
    Observable<Integer> giveEvaluated(Signal parentSignal, ReversibleContext oRev);

    static Observable<Integer> giveEvaluatedHelper(MathExpression me, Signal parentSignal, ReversibleContext oRev, Observable<O> left, Observable<O> right, BiFunction<Integer, Integer, Integer> f) {
        Observable<Integer> evaluated = expressionToEvaluated.computeIfAbsent(me, ($) -> new Observable<>());

        var ref = new Object() {
            Observable<Integer> leftEvaluated = null;
            Observable<Integer> rightEvaluated = null;
        };
        Signal recalcSignal = new Signal(() -> {
            if (ref.leftEvaluated == null || ref.rightEvaluated == null) {
                evaluated.set(null);
            } else {
                evaluated.set(f.apply(ref.leftEvaluated.get(), ref.rightEvaluated.get()));
            }
        });

        oRev.useListenerAndRunNow(left, reversible((_left, leftRev) -> {
            if (_left instanceof MathExpression leftMath) {
                leftRev.useSignalConnection(recalcSignal, parentSignal);
                ref.leftEvaluated = leftMath.giveEvaluated(recalcSignal, leftRev);
            }
        }));
        oRev.useListenerAndRunNow(right, reversible((_right, rightRev) -> {
            if (_right instanceof MathExpression rightMath) {
                rightRev.useSignalConnection(recalcSignal, parentSignal);
                ref.rightEvaluated = rightMath.giveEvaluated(recalcSignal, rightRev);
            }
        }));

        return evaluated;
    }
}

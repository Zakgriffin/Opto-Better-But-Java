package com.zakgriffin.opto.objects.math;

import com.zakgriffin.opto.reactivity.Observable;

import java.util.HashMap;
import java.util.Map;

public class MathExpressionEvaluated {
    public static Map<MathExpression, Observable<Integer>> expressionToEvaluated = new HashMap<>();
//
//    int value;
//
//    MathExpressionEvaluated leftEvaluated;
//    MathExpressionEvaluated rightEvaluated;
//    BiFunction<Integer, Integer, Integer> binaryOperation;
//
//    Signal signal = new Signal(() -> {
//        value = binaryOperation.apply(leftEvaluated.value, rightEvaluated.value);
//    });
//
//
//    public void zoop(BinaryMathExpression me, ReversibleContext oRev) {
//        oRev.useListenerAndRunNow(me.left(), reversible((_left, leftRev) -> {
//            if (_left instanceof MathExpression leftMath) {
//                leftEvaluated = leftMath.evaluated();
//                leftRev.useSignalConnection(leftEvaluated.getSignal(), me.evaluated());
//
//                leftEvaluated.zoop(leftMath, leftRev);
//            }
//        }));
//        oRev.useListenerAndRunNow(me.right(), reversible((_right, rightRev) -> {
//            rightEvaluated = rightMath.evaluated();
//            if (_right instanceof MathExpression rightMath) {
//                rightRev.useSignalConnection(rightEvaluated, me.evaluated());
//
//                leftEvaluated.zoop(rightMath, rightRev);
//            }
//        }));
//    }
//
//    public Signal getSignal() {
//        return signal;
//    }

}

package com.zakgriffin.opto.objects.math;

import com.zakgriffin.opto.Binding;
import com.zakgriffin.opto.Observable;
import com.zakgriffin.opto.objects.IntegerO;
import com.zakgriffin.opto.objects.O;

import java.lang.ref.Reference;
import java.util.List;
import java.util.function.BiFunction;

public interface MathExpression {
    Observable<IntegerO> evaluated();

    static Observable<IntegerO> evaluatedHelper(Observable<O> left, Observable<O> right, BiFunction<Integer, Integer, Integer> f) {
        Observable<IntegerO> evaluated = new Observable<>();

        Observable<Observable<IntegerO>> leftEvaluated = new Observable<>();
        Binding.createBinding(leftEvaluated, () -> {
            if (left.get() instanceof MathExpression leftExpr) {
                return leftExpr.evaluated();
            }
            return null;
        }, List.of(left));

        Observable<Observable<IntegerO>> rightEvaluated = new Observable<>();
        Binding.createBinding(rightEvaluated, () -> {
            if (right.get() instanceof MathExpression rightExpr) {
                return rightExpr.evaluated();
            }
            return null;
        }, List.of(right));


        Binding.createBinding(evaluated, () -> {
            if (leftEvaluated.get() == null || rightEvaluated.get() == null) return null;
            if (leftEvaluated.get().get() == null || rightEvaluated.get().get() == null) return null;

            return new IntegerO(f.apply(leftEvaluated.get().get().i, rightEvaluated.get().get().i));
        }, List.of(leftEvaluated, rightEvaluated));

        return evaluated;
    }

    void main() {
        class A {
            Observable<String> p = new Observable<>();
        }
        Observable<String> x = new Observable<>();
        Observable<A> y = new Observable<>();
        Observable<String> z = new Observable<>();

        Observable<String> yp = new Observable<>();
        y.addListener((a, b, c) -> {
            c.p.addListener();
        });
        Binding.createBinding(yp, () -> {
            return y.get().p.get();
        }, List.of(y));

        Binding.createBinding(z, () -> {
            return x.get() + yp.get();
        }, List.of(x, yp));

        String x = 2;
        Constrained<Integer> bonk = bound(() -> {
           return 3;
        }, List.of());
    }
}

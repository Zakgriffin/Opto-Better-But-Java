package com.zakgriffin.opto.objects.math;

import com.zakgriffin.opto.reactivity.Binding;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.objects.IntegerO;
import com.zakgriffin.opto.objects.O;
import com.zakgriffin.opto.reactivity.Reactivity;

import java.util.List;
import java.util.function.BiFunction;

public interface MathExpression {
    Observable<IntegerO> evaluated();

    static Observable<IntegerO> evaluatedHelper(Observable<O> left, Observable<O> right, BiFunction<Integer, Integer, Integer> f) {
        Observable<IntegerO> evaluated = new Observable<>();

        // child : whenever parent updates, child can be added from any method on parent. child will be removed when next updated

        Observable<IntegerO> leftEvaluated = addDependantsFromUpdate((toFollow) -> {
            O _left = left.get();
            if (!(_left instanceof MathExpression leftExpr)) return;
            return toFollow.add(leftExpr.evaluated());
        }, List.of(left));

        Observable<IntegerO> rightEvaluated = bind((toFollow) -> {
            O _right = right.get();

            if (!(_right instanceof MathExpression rightExpr)) return;

            toFollow.add(rightExpr.evaluated());
            return rightExpr.evaluated().get();
        }, List.of(right));


        Binding.createBinding(evaluated, () -> {
            IntegerO _leftEvaluated = leftEvaluated.get();
            IntegerO _rightEvaluated = rightEvaluated.get();

            if (_leftEvaluated == null || _rightEvaluated == null) return null;

            return new IntegerO(f.apply(_leftEvaluated.i, _rightEvaluated.i));
        }, List.of(leftEvaluated, rightEvaluated));

        return evaluated;
    }

    void main() {
        String a = "a";
        String b = "b";
        String c;

        Observable<String> aObs = new Observable<>(() -> a, (newValue) -> a = newValue);
        Observable<String> bObs = new Observable<>(() -> b, (newValue) -> b = newValue);
        Observable<String> cObs = new Observable<>(() -> c, (newValue) -> c = newValue);

        Binding binding = new Binding(c, () -> {
            return a + b;
        }, List.of(aObs, bObs));

        Reactivity.addBinding(binding);

        class K {
            String p;
        }
        K k = new K();
        String d;

        interface Bonk {
        }
        new Bonk() {
            void set() {
                k = 2;
            }
        };

        Observable<String> dObs = new Observable<>(() -> d, (newValue) -> d = newValue);
        Observable<String> kObs = new Observable<>(() -> k, (newValue) -> k = newValue);
        Observable<String> kpObs = new Observable<>(() -> k.p, (newValue) -> k.p = newValue);

        Binding binding = new Binding(dObs, () -> {
            return k.p;
        }, List.of(kpObs));

        kObs.set(new K());

        kObs.addChild(kpObs);

        kObs.addListener((oldK, newK) -> {

        });

        kObs.addListenerRunNow((oldK, newK) -> {

        });

        // rules:
        // no direct = setting of tracked by observables, must use obs.set();
        // using dot notation implies use of addChild
    }

    void other() {
        interface K {

        }
        class K1 implements K {
            String p;
        }
        class K2 implements K {
            String q;
        }
        K k = new K();
        String d;

        Observable<String> dObs = new Observable<>(() -> d, (newValue) -> d = newValue);
        Observable<String> kObs = new Observable<>(() -> k, (newValue) -> k = newValue);
        Observable<String> kpObs = new Observable<>(() -> k.p, (newValue) -> k.p = newValue);

        Binding binding = new Binding(dObs, () -> {
            if (k instanceof K1 k1) {
                return k1.p;
            }
            return null;
        }, List.of(kpObs));
    }
}

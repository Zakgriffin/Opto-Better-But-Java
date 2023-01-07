package com.zakgriffin.opto.objects.math;

import com.zakgriffin.opto.objects.O;
import com.zakgriffin.opto.reactivity.Tracker;

import java.util.function.BiFunction;

public interface MathExpression {
    Integer evaluate();

    static Tracker<Integer> evaluatedHelper(Tracker<O> left, Tracker<O> right, BiFunction<Integer, Integer, Integer> f) {
        if (left.get() instanceof MathExpression addendExpr && right.get() instanceof MathExpression augendExpr) {
            if (addendExpr.evaluate() == null || augendExpr.evaluate() == null) return null;

            return f.apply(addendExpr.evaluate().get(), augendExpr.evaluate().get());
        }
        return null;

//        Binding.createBinding(evaluated, () -> {
//            if (left.get() instanceof MathExpression addendExpr && right.get() instanceof MathExpression augendExpr) {
//                if (addendExpr.evaluate() == null || augendExpr.evaluate() == null) return null;
//                if (addendExpr.evaluate().get() == null || augendExpr.evaluate().get() == null) return null;
//
//                return f.apply(addendExpr.evaluate().get(), augendExpr.evaluate().get());
//            }
//            return null;
//        }, List.of(left, right));
    }
}

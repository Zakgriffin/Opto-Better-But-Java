package com.zakgriffin.opto.objects.math;

import com.zakgriffin.opto.Binding;
import com.zakgriffin.opto.Observable;
import com.zakgriffin.opto.objects.IntegerO;
import com.zakgriffin.opto.objects.O;

import java.util.List;
import java.util.function.BiFunction;

public interface MathExpression {
    IntegerO evaluated();

    static IntegerO evaluatedHelper(Observable<O> left, Observable<O> right, BiFunction<Integer, Integer, Integer> f) {
        IntegerO evaluated = new IntegerO();

        Binding.createBinding(evaluated.value, () -> {
            if (left.get() instanceof MathExpression leftExpr && right.get() instanceof MathExpression rightExpr) {
                Integer leftInt = leftExpr.evaluated().value.get();
                Integer rightInt = rightExpr.evaluated().value.get();

                if(leftInt == null || rightInt == null) return null;

                return f.apply(leftInt, rightInt);
            }
            return null;
        }, List.of(left, right));

        return evaluated;
    }
}

package com.zakgriffin.opto.objects.math;

import com.zakgriffin.opto.reactivity.Binding;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.objects.IntegerO;
import com.zakgriffin.opto.objects.O;
import com.zakgriffin.opto.reactivity.Reactivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public interface MathExpression {

    static Observable<IntegerO> evaluatedHelper(Observable<O> left, Observable<O> right, BiFunction<Integer, Integer, Integer> f) {
        Observable<IntegerO> evaluated = new Observable<>();

        return evaluated;
    }
}

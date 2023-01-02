package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.*;
import com.zakgriffin.opto.objects.math.MathExpression;
import com.zakgriffin.opto.view.DefaultViewO;
import com.zakgriffin.opto.view.Views;
import javafx.scene.Node;

import java.util.List;

public class IntegerO implements O, DefaultViewO, MathExpression {
    public Observable<Integer> value = new Observable<>();

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return Views.defaultNormalView(this, owningLookupBox);
    }

    @Override
    public NamedObservableO[] namedObservableOs() {
        return new NamedObservableO[]{};
    }

//    IntegerO evaluated = new IntegerO();
    @Override
    public IntegerO evaluated() {
        return this;
//        Binding.createBinding(evaluated.value, () -> {
//            System.out.println("asdasd" + value.get());
//            return value.get();
//        }, List.of(value));
//        return evaluated;
    }
}

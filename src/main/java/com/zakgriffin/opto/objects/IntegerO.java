package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.*;
import com.zakgriffin.opto.objects.math.MathExpression;
import com.zakgriffin.opto.reactivity.Tracker;
import com.zakgriffin.opto.views.DefaultViewO;
import com.zakgriffin.opto.views.Views;
import javafx.scene.Node;

public class IntegerO implements O, DefaultViewO, MathExpression {
    public final int i;

    public IntegerO (int value){
        this.i = value;

        evaluated.set(i);
    }

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return Views.defaultNormalView(this, owningLookupBox);
    }

    @Override
    public NamedObservableO[] namedObservableOs() {
        return new NamedObservableO[]{};
    }

    Tracker<Integer> evaluated = new Tracker<>();
    @Override
    public Tracker<Integer> evaluate() {
        return evaluated;
    }
}

package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.*;
import com.zakgriffin.opto.objects.math.MathExpression;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.view.DefaultViewO;
import com.zakgriffin.opto.view.Views;
import javafx.scene.Node;

public class IntegerO implements O, DefaultViewO, MathExpression {
    public final int i;

    public IntegerO (int value){
        this.i = value;

        evaluated.set(this);
    }

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        return Views.defaultNormalView(this, owningLookupBox);
    }

    @Override
    public NamedObservableO[] namedObservableOs() {
        return new NamedObservableO[]{};
    }

    Observable<IntegerO> evaluated = new Observable<>();
    @Override
    public Observable<IntegerO> evaluated() {
        return evaluated;
    }
}

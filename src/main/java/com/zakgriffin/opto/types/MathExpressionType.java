package com.zakgriffin.opto.types;

import com.zakgriffin.opto.objects.O;
import com.zakgriffin.opto.objects.math.*;

import java.util.Set;

public class MathExpressionType implements TypeO {
    SetType setType = new SetType(Set.of(
            Add.class,
            Subtract.class,
            Multiply.class,
            Divide.class
    ));

    @Override
    public boolean isValid(O o) {
        return setType.isValid(o);
    }
}

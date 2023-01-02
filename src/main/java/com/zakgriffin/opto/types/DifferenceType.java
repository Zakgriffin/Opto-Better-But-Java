package com.zakgriffin.opto.types;

import com.zakgriffin.opto.objects.O;

public class DifferenceType implements TypeO {
    TypeO a;
    TypeO b;

    public DifferenceType(TypeO a, TypeO b) {
        this.a = a;
        this.b = b;
    }
    @Override
    public boolean isValid(O o) {
        return a.isValid(o) && !b.isValid(o);
    }
}

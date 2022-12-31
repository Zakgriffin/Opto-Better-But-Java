package com.zakgriffin.opto.types;

import com.zakgriffin.opto.objects.O;

public class AnyType implements TypeO {
    @Override
    public boolean isValid(O o) {
        return true;
    }
}

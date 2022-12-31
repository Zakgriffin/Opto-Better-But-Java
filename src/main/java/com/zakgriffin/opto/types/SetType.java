package com.zakgriffin.opto.types;

import com.zakgriffin.opto.objects.O;

import java.util.Set;

public class SetType implements TypeO {
    Set<Class<? extends O>> validTypes;

    public SetType(Set<Class<? extends O>> validTypes) {
        this.validTypes = validTypes;
    }

    @Override
    public boolean isValid(O o) {
        for(Class<? extends O> validType : validTypes) {
            if(validType.isInstance(o)) {
                return true;
            }
        }
        return false;
    }
}

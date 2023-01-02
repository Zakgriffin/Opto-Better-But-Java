package com.zakgriffin.opto.types;

import com.zakgriffin.opto.objects.IntegerO;
import com.zakgriffin.opto.objects.O;

import java.util.Set;

public class IntegerSet implements TypeO {
    Set<Integer> integers;
    public IntegerSet(Set<Integer> integers) {
        this.integers = integers;
    }
    @Override
    public boolean isValid(O o) {
        if(o instanceof IntegerO io) {
            return integers.contains(io.integer);
        }
        return false;
    }
}

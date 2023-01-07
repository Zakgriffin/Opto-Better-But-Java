package com.zakgriffin.opto;


import com.zakgriffin.opto.objects.O;
import com.zakgriffin.opto.reactivity.Tracker;
import com.zakgriffin.opto.types.TypeO;

public class NamedObservableO {
    public Tracker<O> obsO;
    public String name;
    public TypeO type;

    public NamedObservableO(Tracker<O> obsO, String name, TypeO type) {
        this.obsO = obsO;
        this.name = name;
        this.type = type;
    }
}
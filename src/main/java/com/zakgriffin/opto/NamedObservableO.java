package com.zakgriffin.opto;


import com.zakgriffin.opto.objects.O;
import com.zakgriffin.opto.types.TypeO;

public class NamedObservableO {
    Observable<O> obsO;
    public String name;
    TypeO type;

    public NamedObservableO(Observable<O> obsO, String name, TypeO type) {
        this.obsO = obsO;
        this.name = name;
        this.type = type;
    }
}
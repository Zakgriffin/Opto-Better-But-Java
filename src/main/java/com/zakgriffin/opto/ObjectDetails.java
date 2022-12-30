package com.zakgriffin.opto;

import com.zakgriffin.opto.objects.*;

import java.util.Arrays;
import java.util.function.Supplier;

public class ObjectDetails {
    public static ObjectDetails[] objectDetails = {
            new ObjectDetails(Add::new, "add"),
            new ObjectDetails(Subtract::new, "subtract"),
            new ObjectDetails(Register::new, "register"),
            new ObjectDetails(DoThen::new, "do_then"),
    };

    Supplier<O> supplier;
    String name;

    public ObjectDetails(Supplier<O> supplier, String name) {
        this.supplier = supplier;
        this.name = name;
    }

    public static ObjectDetails fromName(String name) {
        return Arrays.stream(objectDetails).filter(od -> od.name.equals(name)).findAny().orElse(null);
    }
}

package com.zakgriffin.opto;

import com.zakgriffin.opto.objects.*;

import java.util.Arrays;
import java.util.function.Supplier;

public class ObjectDetails {
    public static ObjectDetails[] objectDetails = {
            new ObjectDetails(com.zakgriffin.opto.objects.Add::new, "add"),
            new ObjectDetails(com.zakgriffin.opto.objects.Subtract::new, "subtract"),
            new ObjectDetails(Register::new, "register"),
            new ObjectDetails(DoThen::new, "do_then"),
            new ObjectDetails(com.zakgriffin.opto.objects.math.Add::new, "+"),
            new ObjectDetails(com.zakgriffin.opto.objects.math.Subtract::new, "-"),
            new ObjectDetails(com.zakgriffin.opto.objects.math.Multiply::new, "*"),
            new ObjectDetails(com.zakgriffin.opto.objects.math.Divide::new, "/"),
            new ObjectDetails(Simplify::new, "simplify"),
            new ObjectDetails(Function::new, "function"),
            new ObjectDetails(Apply::new, "apply"),
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

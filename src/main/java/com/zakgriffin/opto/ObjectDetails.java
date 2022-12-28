package com.zakgriffin.opto;

import com.zakgriffin.opto.objects.*;

import java.util.Arrays;
import java.util.function.Supplier;

import static com.zakgriffin.opto.ObjectType.*;

public class ObjectDetails {
    public static ObjectDetails[] objectDetails = {
            new ObjectDetails(ADD, Add::new, "add"),
            new ObjectDetails(SUBTRACT, Subtract::new, "subtract"),
            new ObjectDetails(REGISTER, Register::new, "register"),
            new ObjectDetails(INTEGER, IntegerO::new, "integer"),
            new ObjectDetails(DO_THEN, DoThen::new, "do_then"),
    };

    ObjectType type;
    Supplier<O> supplier;
    String name;

    public ObjectDetails(ObjectType type, Supplier<O> supplier, String name) {
        this.type = type;
        this.supplier = supplier;
        this.name = name;
    }

    public static ObjectDetails fromName(String name) {
        return Arrays.stream(objectDetails).filter(od -> od.name.equals(name)).findAny().orElse(null);
    }

    public static ObjectDetails fromType(ObjectType type) {
        return Arrays.stream(objectDetails).filter(od -> od.type == type).findAny().orElse(null);
    }
}

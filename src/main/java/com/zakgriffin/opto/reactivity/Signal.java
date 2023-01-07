package com.zakgriffin.opto.reactivity;

import java.util.ArrayList;
import java.util.List;

public class Signal {
    final Runnable runnable;
    final List<Signal> nextSignals = new ArrayList<>();
    int level;

    public Signal(Runnable runnable) {
        this.runnable = runnable;
    }
}

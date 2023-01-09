package com.zakgriffin.opto.reactivity;

import java.util.ArrayList;
import java.util.List;

public class Signal {

    final Runnable runnable;
    final List<Signal> nextSignals = new ArrayList<>();
    final List<Signal> prevSignals = new ArrayList<>();
    int level;

    public Signal(Runnable runnable) {
        this.runnable = runnable;
    }

    public static void addSignalConnection(Signal from, Signal to) {
        from.nextSignals.add(to);
        to.prevSignals.add(from);
        to.propagateUpdateLevel();
    }

    public static void removeSignalConnection(Signal from, Signal to) {
        from.nextSignals.remove(to);
        to.prevSignals.remove(from);
        to.propagateUpdateLevel();
    }

    public void propagateUpdateLevel() {
        int max = 0;
        for(Signal prev : prevSignals) {
            max = Math.max(max, prev.level);
        }
        int newLevel = max + 1;

        if(newLevel <= level) return;
        level = newLevel;

        for(Signal next : nextSignals) {
            next.propagateUpdateLevel();
        }
    }
}

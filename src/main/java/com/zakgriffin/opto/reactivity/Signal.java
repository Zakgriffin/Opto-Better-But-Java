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

    public void trigger() {
        Reactivity.queueSignal(this);
        Reactivity.updateAll();
    }

    public static void main(String[] args) {
        Signal A = new Signal(() -> System.out.println("A"));
        Signal B = new Signal(() -> System.out.println("B"));
        Signal C = new Signal(() -> System.out.println("C"));
        Signal D = new Signal(() -> System.out.println("D"));
        Signal E = new Signal(() -> System.out.println("E"));

        addSignalConnection(D, E);
        addSignalConnection(B, C);
        addSignalConnection(B, D);
        addSignalConnection(A, B);

        A.trigger();
    }
}

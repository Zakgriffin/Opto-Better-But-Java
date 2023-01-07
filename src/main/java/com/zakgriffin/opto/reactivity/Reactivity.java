package com.zakgriffin.opto.reactivity;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Reactivity {
    static final PriorityQueue<Signal> signalsToRun = new PriorityQueue<>(Comparator.comparingInt(obs -> obs.level));
    static boolean alreadyUpdating = false;

    static void addBinding(Binding binding) {

    }

    static void removeBinding(Binding binding) {

    }

    public static void batchUpdate(Runnable runnable) {
        alreadyUpdating = true;
        runnable.run();
        alreadyUpdating = false;
//        updateAll();
    }

//    public static void updateAll() {
//        alreadyUpdating = true;
//        while (!actionsToRun.isEmpty()) {
//            Binding bindingToUpdate = actionsToRun.remove();
//            if (bindingToUpdate.update != null) bindingToUpdate.update.run();
//        }
//        alreadyUpdating = false;
//    }
}
package com.zakgriffin.opto.reactivity;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Reactivity {
    static final PriorityQueue<Action> actionsToRun = new PriorityQueue<>(Comparator.comparingInt(obs -> obs.level));
    static boolean alreadyUpdating = false;

    static void addBinding(Binding binding) {

    }

    static void removeBinding(Binding binding) {

    }

    public static void batchUpdate(Runnable runnable) {
        alreadyUpdating = true;
        runnable.run();
        alreadyUpdating = false;
        updateAll();
    }

    public static void updateAll() {
        alreadyUpdating = true;
        while (!bindingsToUpdate.isEmpty()) {
            Binding bindingToUpdate = bindingsToUpdate.remove();
            if (bindingToUpdate.update != null) bindingToUpdate.update.run();
        }
        alreadyUpdating = false;
    }
}
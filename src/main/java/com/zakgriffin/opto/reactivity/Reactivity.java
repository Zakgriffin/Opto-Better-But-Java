package com.zakgriffin.opto.reactivity;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Reactivity {
    static final PriorityQueue<Signal> signalsToRun = new PriorityQueue<>(Comparator.comparingInt(obs -> obs.level));
//    static boolean alreadyUpdating = false;


//    public static void batchUpdate(Runnable batch) {
//        alreadyUpdating++;
//        batch.run();
//        alreadyUpdating--;
//        updateAll();
//    }

    public static void queueSignal(Signal signal) {
        signalsToRun.add(signal);
    }
    public static void updateAll() {
//        alreadyUpdating = true;
        while (!signalsToRun.isEmpty()) {
            Signal signalToRun = signalsToRun.remove();
            signalToRun.runnable.run();
            for(Signal nextSignal : signalToRun.nextSignals) {
                if(!signalsToRun.contains(nextSignal)) {
                    signalsToRun.add(nextSignal);

                }
            }
        }
//        alreadyUpdating = false;
    }
}
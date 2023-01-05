package com.zakgriffin.opto.reactivity;

import java.util.ArrayList;
import java.util.List;

public class Action {
    final Runnable runnable;
    final List<Action> nextActions = new ArrayList<>();
    int level;

    public Action(Runnable runnable) {
        this.runnable = runnable;
    }
}

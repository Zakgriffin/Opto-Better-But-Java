package com.zakgriffin.opto.reactivity.reversible;

import com.zakgriffin.opto.reactivity.ChangeListener;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.reactivity.Signal;

public class UndoReversibleContext implements ReversibleContext {
    static UndoReversibleContext INSTANCE = new UndoReversibleContext();
    @Override
    public <T> void useListenerAndRunNow(Observable<T> observable, ChangeListener<T> changeListener) {
        observable.removeListener(changeListener);
    }

    @Override
    public void useSignalConnection(Signal from, Signal to) {
        Signal.removeSignalConnection(from, to);
    }

    @Override
    public boolean isDo() {
        return false;
    }
}

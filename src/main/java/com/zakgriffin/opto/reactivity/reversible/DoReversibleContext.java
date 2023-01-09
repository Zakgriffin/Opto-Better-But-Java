package com.zakgriffin.opto.reactivity.reversible;

import com.zakgriffin.opto.reactivity.ChangeListener;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.reactivity.Signal;

public class DoReversibleContext implements ReversibleContext {
    static DoReversibleContext INSTANCE = new DoReversibleContext();
    @Override
    public <T> void useListenerAndRunNow(Observable<T> observable, ChangeListener<T> changeListener) {
        observable.addListenerAndRunNow(changeListener);
    }

    @Override
    public void useSignalConnection(Signal from, Signal to) {
        Signal.addSignalConnection(from, to);
    }

    @Override
    public boolean isDo() {
        return true;
    }
}

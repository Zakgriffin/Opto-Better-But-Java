package com.zakgriffin.opto.reactivity.reversible;

import com.zakgriffin.opto.reactivity.ChangeListener;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.reactivity.Signal;

public interface ReversibleContext {
    <T> void useListenerAndRunNow(Observable<T> observable, ChangeListener<T> changeListener);
    void useSignalConnection(Signal from, Signal to);

    boolean isDo();
}

package com.zakgriffin.opto.reactivity.reversible;

import com.zakgriffin.opto.reactivity.ChangeListener;

public interface ReversibleListener<T> {
    void onChange(T item, ReversibleContext reversibleContext);

    static <T> ChangeListener<T> reversible(ReversibleListener<T> reversibleListener) {
        return (oldValue, newValue) -> {
            reversibleListener.onChange(oldValue, UndoReversibleContext.INSTANCE);
            reversibleListener.onChange(newValue, DoReversibleContext.INSTANCE);
        };
    }
}

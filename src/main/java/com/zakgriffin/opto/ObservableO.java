package com.zakgriffin.opto;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableObjectValue;

import java.util.ArrayList;
import java.util.List;

public class ObservableO implements ObservableObjectValue<O> {
    O o;

    List<ChangeListener<? super O>> changeListeners = new ArrayList<>();
    List<InvalidationListener> invalidationListeners = new ArrayList<>();

    @Override
    public O get() {
        return o;
    }

    public void set(O newO) {
        for(ChangeListener<? super O> changeListener : changeListeners) {
            changeListener.changed(this, this.o, newO);
        }
        this.o = newO;
    }

    @Override
    public void addListener(ChangeListener<? super O> changeListener) {
        changeListeners.add(changeListener);
    }

    @Override
    public void removeListener(ChangeListener<? super O> changeListener) {
        changeListeners.remove(changeListener);
    }

    @Override
    public O getValue() {
        return o;
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        invalidationListeners.add(invalidationListener);
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        invalidationListeners.add(invalidationListener);
    }
}

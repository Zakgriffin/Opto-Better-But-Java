package com.zakgriffin.opto;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableObjectValue;

import java.util.ArrayList;
import java.util.List;

public class Observable<T> implements ObservableObjectValue<T> {
    T value;

    List<ChangeListener<? super T>> changeListeners = new ArrayList<>();
    List<InvalidationListener> invalidationListeners = new ArrayList<>();

    @Override
    public T get() {
        return value;
    }

    public void set(T newValue) {
        T oldValue = value;
        this.value = newValue;
        for(ChangeListener<? super T> changeListener : changeListeners) {
            changeListener.changed(this, oldValue, newValue);
        }
    }

    @Override
    public void addListener(ChangeListener<? super T> changeListener) {
        changeListeners.add(changeListener);
    }

    @Override
    public void removeListener(ChangeListener<? super T> changeListener) {
        changeListeners.remove(changeListener);
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        invalidationListeners.add(invalidationListener);
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        invalidationListeners.add(invalidationListener);
    }

    public static void main(String[] args) {
        // signals, point to other signals to update after
        // when signals update, should update all dependant signals only once

        // observables hold data, can be updated with new object
        // can have children, can listen to reassignment
        // can listen to child updates propagated all the way up
        // bindings

        // for now, no cyclic dependencies, attempting one will cause failure indication "red arrow"
    }
}
package com.zakgriffin.opto.reactivity;

import javafx.beans.value.ChangeListener;

import java.util.ArrayList;
import java.util.List;

public class Observable<T> {
    T value;

    List<ObservableListener<T>> changeListeners = new ArrayList<>();

    public T get() {
        return value;
    }

    public void set(T newValue) {
        T oldValue = value;
        this.value = newValue;
        for(ObservableListener<T> changeListener : changeListeners) {
            changeListener.onChange(oldValue, newValue);
        }
    }

    public void addListener(ObservableListener<T> changeListener) {
        changeListeners.add(changeListener);
    }

    public void removeListener(ObservableListener<T> changeListener) {
        changeListeners.remove(changeListener);
    }

    public static void main(String[] args) {
        // signals, point to other signals to update after
        // when signals update, should update all dependant signals only once

        // observables hold data, can be updated with new object
        // can have children, can listen to reassignment
        // can listen to child updates propagated all the way up

        // bindings can be added and removed

        // for now, no cyclic dependencies, attempting one will cause failure indication "red arrow"
    }
}
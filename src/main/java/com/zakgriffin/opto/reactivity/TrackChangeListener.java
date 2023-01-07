package com.zakgriffin.opto.reactivity;

public interface TrackChangeListener<T> {
    void onChange(T oldValue, T newValue);
}

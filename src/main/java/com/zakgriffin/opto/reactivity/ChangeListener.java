package com.zakgriffin.opto.reactivity;

public interface ChangeListener<T> {
    void onChange(T oldValue, T newValue);
}

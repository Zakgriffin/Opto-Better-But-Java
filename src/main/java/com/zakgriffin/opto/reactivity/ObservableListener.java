package com.zakgriffin.opto.reactivity;

public interface ObservableListener<T> {
    void onChange(T oldValue, T newValue);
}

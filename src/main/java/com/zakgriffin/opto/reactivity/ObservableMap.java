package com.zakgriffin.opto.reactivity;

import java.util.HashMap;
import java.util.Map;

public class ObservableMap<Key, Value> {
    private Map<Key, Observable<Value>> map = new HashMap<>();

    public void put(Key key, Value value) {
        Observable<Value> valueObs = map.computeIfAbsent(key, ($) -> new Observable<>());
        valueObs.set(value);
    }

    public Observable<Value> get(Key key) {
        return map.get(key);
    }

    public Observable<Value> computeIfAbsent(Key key) {
        return map.computeIfAbsent(key, ($) -> new Observable<>());
    }
}

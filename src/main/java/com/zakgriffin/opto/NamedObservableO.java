package com.zakgriffin.opto;


public class NamedObservableO {
    ObservableO obsO;
    public String name;

    public NamedObservableO(ObservableO obsO, String name) {
        this.obsO = obsO;
        this.name = name;
    }
}
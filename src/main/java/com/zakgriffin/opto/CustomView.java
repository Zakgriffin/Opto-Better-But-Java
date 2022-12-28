package com.zakgriffin.opto;

import javafx.scene.control.TextField;

public interface CustomView {
    FreshVisualPair getView(O object, TextField head);
}

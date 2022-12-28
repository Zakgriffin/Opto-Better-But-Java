package com.zakgriffin.opto;

import javafx.scene.Node;

public class FreshVisualPair {
    Node visual;
    Node nextToFocus;

    public FreshVisualPair(Node visual, Node nextToFocus) {
        this.visual = visual;
        this.nextToFocus = nextToFocus;
    }
}

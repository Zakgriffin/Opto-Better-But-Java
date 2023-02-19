package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.LookupBox;
import com.zakgriffin.opto.Point;
import com.zakgriffin.opto.reactivity.Observable;
import javafx.scene.Group;
import javafx.scene.Node;

public class PositionedO implements O{
    Observable<O> o = new Observable<>();
    Observable<Point> position = new Observable<>();


    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        Group container = new Group();

        return container;
    }
}

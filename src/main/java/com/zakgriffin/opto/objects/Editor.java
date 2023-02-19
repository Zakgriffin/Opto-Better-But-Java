package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.LookupBox;
import com.zakgriffin.opto.Point;
import com.zakgriffin.opto.reactivity.ObservableSet;
import javafx.scene.Group;
import javafx.scene.Node;

public class Editor implements O {
    ObservableSet<PositionedO> positionedOs = new ObservableSet<>();
    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        Group container = new Group();
//        positionedOs.each((positionedO) -> {
//            Node view = positionedO.getView();
//
//            view.onDrag((e) -> {
//               positionedO.position.set(new Point(e.x, e.y));
//            });
//
//            rev.addListener(positionedO.position, (_position) -> {
//                view.setTranslateX(_position.x);
//                view.setTranslateY(_position.y);
//            });
//            container.getChildren().add(view);
//        });

        return container;
    }
}

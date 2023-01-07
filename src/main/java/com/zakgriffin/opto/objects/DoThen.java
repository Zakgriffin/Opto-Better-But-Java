package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.*;
import com.zakgriffin.opto.reactivity.Tracker;
import com.zakgriffin.opto.types.SetType;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Set;

public class DoThen implements O {
    Tracker<O> effect = new Tracker<>();
    Tracker<O> nextDoThen = new Tracker<>();

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        VBox objectContainer = new VBox();

        HBox hbox = new HBox();
        objectContainer.getChildren().add(hbox);

        hbox.getChildren().add(owningLookupBox.getTextField());

        LookupBox effectLookupBox = new LookupBox(effect, "effect", (oldNode, newNode) -> {
            hbox.getChildren().remove(oldNode);
            hbox.getChildren().add(1, newNode);
        });
        hbox.getChildren().add(effectLookupBox.getTextField());
        LookupBox.typeHelper(effectLookupBox, new SetType(Set.of(Add.class, Subtract.class)));

        LookupBox nextDoThenLookupBox = new LookupBox(nextDoThen, "next_do_then", (oldNode, newNode) -> {
            objectContainer.getChildren().remove(oldNode);
            objectContainer.getChildren().add(1, newNode);
        });
        LookupBox.typeHelper(nextDoThenLookupBox, new SetType(Set.of(DoThen.class)));

        objectContainer.getChildren().add(nextDoThenLookupBox.getTextField());

        return objectContainer;
    }
}

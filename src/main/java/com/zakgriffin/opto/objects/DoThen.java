package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.*;
import javafx.beans.property.Property;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DoThen implements O {
    Property<O> effect;
    Property<O> nextDoThen;

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        VBox objectContainer = new VBox();

        HBox hbox = new HBox();
        objectContainer.getChildren().add(hbox);
        Node lookupBoxNode = owningLookupBox.getNode();
        hbox.getChildren().add(lookupBoxNode);

        LookupBox effectLookupBox = new LookupBox(
                (child) -> hbox.getChildren().add(child),
                (child) -> hbox.getChildren().remove(child)
        );
        effectLookupBox.setPrompt("effect");

        LookupBox nextDoThenLookupBox = new LookupBox(
                (child) -> objectContainer.getChildren().add(child),
                (child) -> objectContainer.getChildren().remove(child)
        );
        nextDoThenLookupBox.setPrompt("next_do_then");

        return objectContainer;
    }
}

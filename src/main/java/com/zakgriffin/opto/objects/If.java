package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.LookupBox;
import com.zakgriffin.opto.O;
import javafx.beans.property.Property;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class If implements O {
    Property<O> condition;
    Property<O> then;

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
        effectLookupBox.setPrompt("condition");

        LookupBox nextDoThenLookupBox = new LookupBox(
                (child) -> {
                    objectContainer.getChildren().add(child);
                    child.setTranslateX(20);
                },
                (child) -> objectContainer.getChildren().remove(child)
        );
        nextDoThenLookupBox.setPrompt("then");

        return objectContainer;
    }
}

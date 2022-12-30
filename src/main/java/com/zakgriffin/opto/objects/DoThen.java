package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.*;
import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DoThen implements O {
    ObservableO effect = new ObservableO();
    ObservableO nextDoThen = new ObservableO();

    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        VBox objectContainer = new VBox();

        HBox hbox = new HBox();
        objectContainer.getChildren().add(hbox);

        hbox.getChildren().add(owningLookupBox.getTextField());

        new LookupBox(effect, "effect", (oldNode, newNode) -> {
            hbox.getChildren().remove(oldNode);
            hbox.getChildren().add(newNode);
        });

        new LookupBox(effect, "next_do_then", (oldNode, newNode) -> {
            objectContainer.getChildren().remove(oldNode);
            objectContainer.getChildren().add(newNode);
        });

        return objectContainer;
    }
}

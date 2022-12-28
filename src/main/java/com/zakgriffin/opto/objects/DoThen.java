package com.zakgriffin.opto.objects;

import com.zakgriffin.opto.*;
import javafx.beans.property.Property;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DoThen implements O , CustomView {
    Property<O> effect;
    Property<O> nextDoThen;

    @Override
    public ObjectType getType() {
        return ObjectType.DO_THEN;
    }

    @Override
    public ObjectProperty[] properties() {
        return new ObjectProperty[] {
                new ObjectProperty(effect, "effect"),
                new ObjectProperty(nextDoThen, "next_do_then")
        };
    }

    @Override
    public FreshVisualPair getView(O object, TextField head) {
        VBox objectContainer = new VBox();
        Opto.styleDefaultTextField(head);
        objectContainer.getChildren().add(head);

        VBox vBox = new VBox();
        objectContainer.getChildren().add(vBox);

        ObjectProperty[] objectProperties = object.properties();


        ObjectProperty effectProperty = objectProperties[0];
        LookupBox lookupBox = new LookupBox(
                (child) -> objectContainer.getChildren().add(1, child),
                (child) -> objectContainer.getChildren().remove(child)
        );
        TextField textField = lookupBox.getTextField();
        textField.setPromptText(effectProperty.name);


        ObjectProperty nextDoThenProperty = objectProperties[1];
        LookupBox lookupBox2 = new LookupBox(
                (child) -> vBox.getChildren().add(child),
                (child) -> vBox.getChildren().remove(child)
        );
        TextField textField2 = lookupBox2.getTextField();
        textField2.setPromptText(nextDoThenProperty.name);


        return new FreshVisualPair(objectContainer, objectContainer.getChildren().get(1));
    }
}

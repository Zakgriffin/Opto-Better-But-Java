package com.zakgriffin.opto;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class NormalView {
    public static Node defaultNormalView(DefaultViewO object, LookupBox owningLookupBox) {
        HBox objectContainer = new HBox();

        Node node = owningLookupBox.getNode();
        objectContainer.getChildren().add(node);

        ObjectProperty[] objectProperties = object.properties();
        for (int i = 0; i < objectProperties.length; i++) {
            ObjectProperty objectProperty = objectProperties[i];
            createChildBox(objectContainer, objectProperty.name, i + 1);
        }

        return objectContainer;
    }

    private static void createChildBox(HBox parent, String name, int index) {
        LookupBox lookupBox = new LookupBox(
                (child) -> parent.getChildren().add(index, child),
                (child) -> parent.getChildren().remove(child)
        );
        lookupBox.setPrompt(name);
    }
}

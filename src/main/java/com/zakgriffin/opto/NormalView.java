package com.zakgriffin.opto;

import javafx.scene.Node;
import javafx.scene.layout.*;

public class NormalView {
    public static Node defaultNormalView(DefaultViewO object, LookupBox owningLookupBox) {
        HBox objectContainer = new HBox();

        Node textField = owningLookupBox.getTextField();
        objectContainer.getChildren().add(textField);

        NamedObservableO[] namedObservableOs = object.namedObservableOs();

        for (int i = 0; i < namedObservableOs.length; i++) {
            NamedObservableO namedObservableO = namedObservableOs[i];

            int finalI = i;
            LookupBox lookupBox = new LookupBox(namedObservableO.obsO, namedObservableO.name, (oldNode, newNode) -> {
                objectContainer.getChildren().remove(oldNode);
                objectContainer.getChildren().add(finalI + 1, newNode);
            });
            objectContainer.getChildren().add(lookupBox.getTextField());
        }

        return objectContainer;
    }
}

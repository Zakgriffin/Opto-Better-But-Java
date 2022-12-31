package com.zakgriffin.opto;

import com.zakgriffin.opto.objects.DoThen;
import com.zakgriffin.opto.types.SetType;
import javafx.scene.Node;
import javafx.scene.layout.*;

import java.util.Set;

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
            LookupBox.typeHelper(lookupBox, namedObservableO.type);
            objectContainer.getChildren().add(lookupBox.getTextField());
        }

        return objectContainer;
    }
}

package com.zakgriffin.opto.view;

import com.zakgriffin.opto.LookupBox;
import com.zakgriffin.opto.NamedObservableO;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class Views {
    public static Node defaultNormalView(DefaultViewO object, LookupBox owningLookupBox) {
        HBox objectContainer = new HBox();
        return bah(objectContainer, object, owningLookupBox);
    }

    public static Node verticalView(DefaultViewO object, LookupBox owningLookupBox) {
        VBox objectContainer = new VBox();
        return bah(objectContainer, object, owningLookupBox);
    }

    private static Node bah(Pane objectContainer, DefaultViewO object, LookupBox owningLookupBox) {
        Node textField = owningLookupBox.getTextField();
        objectContainer.getChildren().add(textField);

        NamedObservableO[] namedObservableOs = object.namedObservableOs();

        for (int i = 0; i < namedObservableOs.length; i++) {
            addChildView(objectContainer, namedObservableOs[i], i + 1);
        }

        return objectContainer;
    }

    public static Node infixView(InfixViewO object, LookupBox owningLookupBox) {
        HBox objectContainer = new HBox();

        TextField textField = owningLookupBox.getTextField();

        Color fillColor = Color.rgb(0x33,0x33,0x33);

        Arc leftCap = new Arc();
        leftCap.setRadiusX(3);
        leftCap.setRadiusY(12);
        leftCap.setStartAngle(90);
        leftCap.setLength(180);
        leftCap.setType(ArcType.OPEN);
        leftCap.setStroke(Color.WHITE);
        leftCap.setFill(fillColor);

        Arc rightCap = new Arc();
        rightCap.setRadiusX(3);
        rightCap.setRadiusY(12);
        rightCap.setStartAngle(-90);
        rightCap.setLength(180);
        rightCap.setType(ArcType.OPEN);
        rightCap.setStroke(Color.WHITE);
        rightCap.setFill(fillColor);

        objectContainer.getChildren().add(0, leftCap);
        addChildView(objectContainer, object.left(), 1);
        objectContainer.getChildren().add(2, textField);
        addChildView(objectContainer, object.right(), 3);
        objectContainer.getChildren().add(4, rightCap);

        return objectContainer;
    }

    private static void addChildView(Pane objectContainer, NamedObservableO namedObservableO, int addIndex) {
        LookupBox lookupBox = new LookupBox(namedObservableO.obsO, namedObservableO.name, (oldNode, newNode) -> {
            objectContainer.getChildren().remove(oldNode);
            objectContainer.getChildren().add(addIndex, newNode);
        });
        LookupBox.typeHelper(lookupBox, namedObservableO.type);
        objectContainer.getChildren().add(lookupBox.getTextField());
    }
}

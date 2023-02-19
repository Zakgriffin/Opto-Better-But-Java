package com.zakgriffin.opto.views;

import com.zakgriffin.opto.LookupBox;
import com.zakgriffin.opto.NamedObservableO;
import com.zakgriffin.opto.Opto;
import com.zakgriffin.opto.Point;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class Views {
    public static Color BACKGROUND_COLOR = Color.rgb(0x11, 0x11, 0x11);
    public static Background LOOKUP_BOX_BACKGROUND = colorBackground(Color.rgb(0x22, 0x22, 0x22));
    public static Background HOVERED_BACKGROUND = colorBackground(Color.rgb(0x33, 0x33, 0x33));
    public static Background BOUND_BACKGROUND = colorBackground(Color.rgb(0x22, 0x22, 0x33));

    public static Background colorBackground(Color color) {
        return new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
    }

    public static Node horizontalView(DefaultViewO object, LookupBox owningLookupBox) {
        HBox objectContainer = new HBox();
        return linearView(objectContainer, object, owningLookupBox);
    }

    public static Node verticalView(DefaultViewO object, LookupBox owningLookupBox) {
        VBox objectContainer = new VBox();
        return linearView(objectContainer, object, owningLookupBox);
    }

    private static Node linearView(Pane objectContainer, DefaultViewO object, LookupBox owningLookupBox) {
        TextField textField = owningLookupBox.getTextField();
        objectContainer.getChildren().add(textField);

        NamedObservableO[] namedObservableOs = object.namedObservableOs();

        for (int i = 0; i < namedObservableOs.length; i++) {
            addChildLookupBox(objectContainer, namedObservableOs[i], i + 1);
        }

        makeDraggable(objectContainer, textField);
        return objectContainer;
    }

    public static Node infixView(InfixViewO object, LookupBox owningLookupBox) {
        HBox objectContainer = new HBox();

        TextField textField = owningLookupBox.getTextField();

        Color fillColor = Color.rgb(0x33, 0x33, 0x33);

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
        addChildLookupBox(objectContainer, object.left(), 1);
        objectContainer.getChildren().add(2, textField);
        addChildLookupBox(objectContainer, object.right(), 3);
        objectContainer.getChildren().add(4, rightCap);

        return objectContainer;
    }

    private static void addChildLookupBox(Pane objectContainer, NamedObservableO namedObservableO, int addIndex) {
        LookupBox lookupBox = new LookupBox(namedObservableO.obsO, namedObservableO.name, (oldNode, newNode) -> {
            objectContainer.getChildren().remove(oldNode);
            objectContainer.getChildren().add(addIndex, newNode);
        });
        LookupBox.typeHelper(lookupBox, namedObservableO.type);
        objectContainer.getChildren().add(lookupBox.getTextField());
    }

    public static void makeDraggable(Node node, TextField textField) {
//        dragHandle.visibleProperty().bind(node.hoverProperty());
//        Opto.root.setOnKeyPressed((g) -> {
//            textField.setEditable(false);
//        });
//
//        Opto.root.setOnKeyReleased((g) -> {
//            textField.setEditable(true);
//        });


//        textField.setOnMouseEntered((e) -> {
//            textField.setBackground(HOVERED_BACKGROUND);
//        });
//
//        textField.setOnMouseExited((e) -> {
//            textField.setBackground(LOOKUP_BOX_BACKGROUND);
//        });
//
        Point dragOffset = new Point(0, 0);
        textField.setOnMousePressed((mouseEvent) -> {
            if(!mouseEvent.isAltDown()) return;
            dragOffset.x = node.getTranslateX() - mouseEvent.getSceneX();
            dragOffset.y = node.getTranslateY() - mouseEvent.getSceneY();
        });
        textField.setOnMouseDragged((mouseEvent) -> {
            if(!mouseEvent.isAltDown()) return;
            node.setTranslateX(mouseEvent.getSceneX() + dragOffset.x);
            node.setTranslateY(mouseEvent.getSceneY() + dragOffset.y);
        });
    }
}

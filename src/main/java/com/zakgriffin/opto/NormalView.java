package com.zakgriffin.opto;

import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class NormalView {
    public static FreshVisualPair defaultNormalView(O object, TextField head) {
        HBox objectContainer = new HBox();
        Opto.styleDefaultTextField(head);
//        head.setDisable(true);
        objectContainer.getChildren().add(head);

//        Point dragOffset = new Point(0, 0);
//        objectContainer.setOnMousePressed((MouseEvent mouseEvent) -> {
//            dragOffset.x = objectContainer.getTranslateX() - mouseEvent.getSceneX();
//            dragOffset.y = objectContainer.getTranslateY() - mouseEvent.getSceneY();
//        });
//        objectContainer.setOnMouseDragged((mouseEvent) -> {
//            objectContainer.setTranslateX(mouseEvent.getSceneX() + dragOffset.x);
//            objectContainer.setTranslateY(mouseEvent.getSceneY() + dragOffset.y);
//        });

        ObjectProperty[] objectProperties = object.properties();
        for (int i = 0; i < objectProperties.length; i++) {
            ObjectProperty objectProperty = objectProperties[i];
            createChildBox(objectContainer, objectProperty.name, i + 1);
        }

        return new FreshVisualPair(objectContainer, objectContainer.getChildren().get(1));
    }

    private static void createChildBox(HBox parent, String name, int index) {
        LookupBox lookupBox = new LookupBox(
                (child) -> parent.getChildren().add(index, child),
                (child) -> parent.getChildren().remove(child)
        );
        TextField textField = lookupBox.getTextField();
        textField.setPromptText(name);
    }
}

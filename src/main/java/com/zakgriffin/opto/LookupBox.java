package com.zakgriffin.opto;

import com.zakgriffin.opto.objects.IntegerO;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.util.Objects;
import java.util.function.BiConsumer;


public class LookupBox {
    TextField textField = new TextField();
    Node workingNode = textField;

    public LookupBox(ObservableO obsO, String prompt, BiConsumer<Node, Node> replaceNode) {
        textField.setPromptText(prompt);

        Opto.styleDefaultTextField(textField);

        textField.textProperty().addListener((observable, oldText, newText) -> {
            if(newText.equals(oldText)) return;

            O o = resolveText(newText);
            obsO.set(o);
        });

        obsO.addListener((observable, oldO, newO) -> {
            Node newWorkingNode = newO == null ? textField : newO.getNormalView(this);
            replaceNode.accept(workingNode, newWorkingNode);
            workingNode = newWorkingNode;
        });
    }

    private static O resolveText(String text) {
        ObjectDetails objectDetails = ObjectDetails.fromName(text);
        if(objectDetails != null) {
            return objectDetails.supplier.get();
        }
        try {
            int i = Integer.parseInt(text);
            return new IntegerO(i);
        } catch (NumberFormatException ignored) {}

        return null;
    }

    public TextField getTextField() {
        return textField;
    }

//    public void makeDraggable(Node dragHandle) {
//        dragHandle.visibleProperty().bind(node.hoverProperty());
//
//        Point dragOffset = new Point(0, 0);
//        dragHandle.setOnMousePressed((MouseEvent mouseEvent) -> {
//            dragOffset.x = node.getParent().getTranslateX() - mouseEvent.getSceneX();
//            dragOffset.y = node.getParent().getTranslateY() - mouseEvent.getSceneY();
//        });
//        dragHandle.setOnMouseDragged((mouseEvent) -> {
//            node.getParent().setTranslateX(mouseEvent.getSceneX() + dragOffset.x);
//            node.getParent().setTranslateY(mouseEvent.getSceneY() + dragOffset.y);
//        });
//    }
}

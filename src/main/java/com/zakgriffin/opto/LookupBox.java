package com.zakgriffin.opto;

import com.zakgriffin.opto.objects.IntegerO;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.util.function.Consumer;

public class LookupBox {
    private Node workingVisual;
    private final Node node;
    private final TextField textField;
    private final Consumer<Node> addChild;
    private final Consumer<Node> removeChild;

    public LookupBox(Consumer<Node> addChild, Consumer<Node> removeChild) {
        this.addChild = addChild;
        this.removeChild = removeChild;

        Group group = new Group();

        textField = new TextField();
        group.getChildren().add(textField);

        Rectangle dragHandle = new Rectangle(8, 8);

        group.getChildren().add(dragHandle);

        node = group;
        Opto.styleDefaultTextField(textField);
        addKeyPressListener();

        makeDraggable(dragHandle);
    }

    public void makeDraggable(Node dragHandle) {
        dragHandle.visibleProperty().bind(node.hoverProperty());

        Point dragOffset = new Point(0, 0);
        dragHandle.setOnMousePressed((MouseEvent mouseEvent) -> {
            dragOffset.x = node.getParent().getTranslateX() - mouseEvent.getSceneX();
            dragOffset.y = node.getParent().getTranslateY() - mouseEvent.getSceneY();
        });
        dragHandle.setOnMouseDragged((mouseEvent) -> {
            node.getParent().setTranslateX(mouseEvent.getSceneX() + dragOffset.x);
            node.getParent().setTranslateY(mouseEvent.getSceneY() + dragOffset.y);
        });
    }

    public void addKeyPressListener() {
        textField.setOnKeyPressed((keyPressEvent) -> Platform.runLater(() -> {
            if (workingVisual != null) {
                if (keyPressEvent.getCode() == KeyCode.ENTER) {
                    return;
                }
                removeChild.accept(workingVisual);
                addChild.accept(node);
                workingVisual = null;
            }
            String text = textField.getText();

            O o = resolveText(text);
            if(o == null) return;

            removeChild.accept(node);

            workingVisual = o.getNormalView(this);

            addChild.accept(workingVisual);
        }));
        addChild.accept(node);
    }

    private O resolveText(String text) {
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

    public Node getNode() {
        return node;
    }

    public void setPrompt(String promptText) {
        textField.setPromptText(promptText);
    }

    public void requestFocus() {
        Platform.runLater(textField::requestFocus);
    }
}

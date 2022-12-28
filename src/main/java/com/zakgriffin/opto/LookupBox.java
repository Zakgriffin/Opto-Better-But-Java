package com.zakgriffin.opto;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.util.function.Consumer;

public class LookupBox {
    private FreshVisualPair workingPair;
    final private TextField textField;

    private final Consumer<Node> addChild;
    private final Consumer<Node> removeChild;

    public LookupBox(Consumer<Node> addChild, Consumer<Node> removeChild) {
        this.addChild = addChild;
        this.removeChild = removeChild;

        textField = new TextField();
        Opto.styleDefaultTextField(textField);
        addKeyPressListener();
    }

    public void addKeyPressListener() {
        textField.setOnKeyPressed((keyPressEvent) -> Platform.runLater(() -> {
            if (workingPair != null) {
                if (keyPressEvent.getCode() == KeyCode.ENTER) {
                    workingPair.nextToFocus.requestFocus();
                    return;
                }
                removeChild.accept(workingPair.visual);
                addChild.accept(textField);
                workingPair = null;
            }
            String oName = textField.getText();

            ObjectDetails objectDetails = ObjectDetails.fromName(oName);
            if (objectDetails == null) return;

            removeChild.accept(textField);

            O o = objectDetails.supplier.get();
            if (o instanceof CustomView oc) {
                workingPair = oc.getView(o, textField);
            } else {
                workingPair = NormalView.defaultNormalView(o, textField);
            }

            addChild.accept(workingPair.visual);
        }));
        addChild.accept(textField);
    }

    public void requestFocus() {
        Platform.runLater(textField::requestFocus);
    }

    public TextField getTextField() {
        return textField;
    }

}

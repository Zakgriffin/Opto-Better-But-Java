package com.zakgriffin.opto;

import com.zakgriffin.opto.objects.IntegerO;
import com.zakgriffin.opto.objects.O;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.types.TypeO;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.util.function.BiConsumer;

public class LookupBox {
    TextField textField = new TextField();
    Node workingNode = textField;
    Observable<O> obsO;

    public LookupBox(Observable<O> obsO, String prompt, BiConsumer<Node, Node> replaceNode) {
        this.obsO = obsO;
        textField.setPromptText(prompt);

        Opto.styleDefaultTextField(textField);

        textField.textProperty().addListener((observable, oldText, newText) -> {
            if (newText.equals(oldText)) return;

            O o = resolveText(newText);
            obsO.set(o);
        });

        obsO.addListener((oldO, newO) -> {
            Node newWorkingNode = newO == null ? textField : newO.getNormalView(this);
            replaceNode.accept(workingNode, newWorkingNode);
            workingNode = newWorkingNode;
        });
    }

    private static O resolveText(String text) {
        ObjectDetails objectDetails = ObjectDetails.fromName(text);
        if (objectDetails != null) {
            return objectDetails.supplier.get();
        }
        try {
            int i = Integer.parseInt(text);
            return new IntegerO(i);
        } catch (NumberFormatException ignored) {
        }

        return null;
    }

    public TextField getTextField() {
        return textField;
    }

    public void setValidType(Observable<TypeO> typeObs) {
        BiConsumer<O, TypeO> p = (o, type) -> Platform.runLater(() -> {
            String x;
            if (obsO.get() == null) x = "#EED202";
            else if (!typeObs.get().isValid(obsO.get())) x = "red";
            else x = "transparent";

            textField.setStyle(
                "-fx-text-fill: white;\n"+
                "-fx-border-color: "+ x + ";\n" +
                "-fx-border-width: 0 0 1 0;"
            );
        });
        obsO.addListener((oldO, newO) -> p.accept(newO, typeObs.get()));
        typeObs.addListener((oldType, newType) -> p.accept(obsO.get(), newType));
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

    public static void typeHelper(LookupBox lookupBox, TypeO type) {
        Observable<TypeO> nextDoThenType = new Observable<>();
        lookupBox.setValidType(nextDoThenType);
        nextDoThenType.set(type);
    }
}

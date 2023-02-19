package com.zakgriffin.opto;

import com.zakgriffin.opto.reactivity.Binding;

import com.zakgriffin.opto.objects.IntegerO;
import com.zakgriffin.opto.objects.O;
import com.zakgriffin.opto.reactivity.Observable;
import com.zakgriffin.opto.types.TypeO;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.util.function.BiConsumer;

import static com.zakgriffin.opto.views.Views.BOUND_BACKGROUND;
import static com.zakgriffin.opto.views.Views.LOOKUP_BOX_BACKGROUND;

public class LookupBox {
    TextField textField = new TextField();
    Node workingNode = textField;
    Observable<O> obsO;

    public LookupBox(Observable<O> obsO, String prompt, BiConsumer<Node, Node> replaceNode) {
        this.obsO = obsO;
        textField.setPromptText(prompt);

        Opto.styleDefaultTextField(textField);

        Binding.getObsBinding(obsO).addListenerAndRunNow((oldBinding, newBinding) -> {
            textField.setEditable(newBinding == null);
            textField.setBackground(newBinding == null ? LOOKUP_BOX_BACKGROUND : BOUND_BACKGROUND);
        });

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
                    "-fx-text-fill: white;\n" +
                            "-fx-border-color: " + x + ";\n" +
                            "-fx-border-width: 0 0 1 0;"
            );
        });
        obsO.addListener((oldO, newO) -> p.accept(newO, typeObs.get()));
        typeObs.addListener((oldType, newType) -> p.accept(obsO.get(), newType));
    }

    public static void typeHelper(LookupBox lookupBox, TypeO type) {
        Observable<TypeO> nextDoThenType = new Observable<>();
        lookupBox.setValidType(nextDoThenType);
        nextDoThenType.set(type);
    }
}

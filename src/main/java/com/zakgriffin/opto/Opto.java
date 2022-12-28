package com.zakgriffin.opto;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Opto extends Application {
    public static Group root = new Group();
    public static Font ROBOTO_MONO = Font.loadFont(Opto.class.getResourceAsStream("/RobotoMono-Regular.ttf"), 12);

    public static Color backgroundColor = Color.rgb(0x11, 0x11, 0x11);

    public static Border INPUT_BOX_BORDER = new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    public static Background INPUT_BOX_BACKGROUND = new Background(new BackgroundFill(Color.rgb(0x22, 0x22, 0x22), CornerRadii.EMPTY, Insets.EMPTY));

    public static void whiteText(Node node) {
        node.setStyle("-fx-text-fill: white;");
    }

    public static void resizeToFit(TextField textField) {
        bonk(textField);
        textField.textProperty().addListener((a, b, newValue) -> bonk(textField));
        textField.promptTextProperty().addListener((a, b, newValue) -> bonk(textField));
    }

    private static void bonk(TextField textField) {
        double perLetter = 7.2;
        double constant = 16;

        int x = textField.textProperty().get().length();
        int k = x == 0 ? textField.getPromptText().length() : x;
        textField.setPrefWidth(k * perLetter + constant);
    }

    public static void styleDefaultTextField(TextField textField) {
        textField.setBackground(INPUT_BOX_BACKGROUND);
        textField.setFont(ROBOTO_MONO);
        whiteText(textField);

        resizeToFit(textField);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(root, 1600, 800);
        scene.setFill(backgroundColor);

        scene.setOnMouseClicked((clickEvent) -> {
            if (clickEvent.getClickCount() == 1) {
                Platform.runLater(root::requestFocus);
                return;
            }

            if (clickEvent.getClickCount() != 2) return;

            HBox hbox = new HBox();
            root.getChildren().add(hbox);

            LookupBox lookupBox = new LookupBox(
                    (child) -> hbox.getChildren().add(child),
                    (child) -> hbox.getChildren().remove(child)
            );

            lookupBox.requestFocus();
            hbox.setTranslateX(clickEvent.getX());
            hbox.setTranslateY(clickEvent.getY());
        });

        stage.setTitle("Opto");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
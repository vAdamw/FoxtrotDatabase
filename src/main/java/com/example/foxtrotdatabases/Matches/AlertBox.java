package com.example.foxtrotdatabases.Matches;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AlertBox {
    public static void showAlertBox(String message){
        Stage stage = new Stage();
        stage.setMinWidth(350);
        stage.setMinHeight(150);
        stage.setTitle("Felmeddelande");

        Label label = new Label(message);
        label.setFont(new Font("ROMAN", 20));
        Button button = new Button("Close");
        button.setOnAction(e -> stage.close());


        VBox vBox = new VBox(label, button);
        vBox.setSpacing(35);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);

        stage.setScene(scene);
        stage.showAndWait();

    }
}

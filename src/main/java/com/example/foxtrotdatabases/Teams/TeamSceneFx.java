package com.example.foxtrotdatabases.Teams;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.w3c.dom.Text;

public class TeamSceneFx {
    public TeamSceneFx() {
    }

    BorderPane teamsBorderPane = new BorderPane();

    public Scene addToTeamScene(Button button) {
        HBox hBox = new HBox(button);
        Scene scene = new Scene(hBox, 900, 700);
        return scene;

    }

    public VBox presentTeamView() {
        VBox vBox = new VBox();
        vBox.setPrefHeight(800);
        vBox.setPrefWidth(600);
        vBox.setStyle("-fx-background-color: linear-gradient(#020024, #053cba,#0540bc ,#00cefc,#040044 );");

        return vBox;
    }


}

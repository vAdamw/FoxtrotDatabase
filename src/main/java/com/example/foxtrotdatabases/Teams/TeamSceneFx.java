package com.example.foxtrotdatabases.Teams;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class TeamSceneFx {
    public TeamSceneFx(){
    }
    public Scene addToTeamsScene(Button button){
        HBox hBox = new HBox(button);
        Scene scene = new Scene(hBox, 900, 700);
        return scene;
    }
}
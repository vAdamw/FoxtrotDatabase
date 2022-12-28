package com.example.foxtrotdatabases.Matches;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class MatchSceneFx {
    public MatchSceneFx(){
    }
    public Scene addToMatchScene(Button button){
        HBox hBox = new HBox(button);
        Scene scene = new Scene(hBox, 900, 700);
        return scene;
    }
}
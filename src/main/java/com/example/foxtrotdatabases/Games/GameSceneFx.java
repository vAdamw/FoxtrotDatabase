package com.example.foxtrotdatabases.Games;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class GameSceneFx {
    public GameSceneFx(){
    }
    public Scene addToGameScene(Button button){
        HBox hBox = new HBox(button);
        Scene scene = new Scene(hBox, 900, 700);
        return scene;
    }
}

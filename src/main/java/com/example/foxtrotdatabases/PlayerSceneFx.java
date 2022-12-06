package com.example.foxtrotdatabases;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class PlayerSceneFx {
    public PlayerSceneFx(){
    }
    public Scene addToPlayerScene(Button button){
        HBox hBox = new HBox(button);
        Scene scene = new Scene(hBox, 900, 700);
        return scene;
    }
}

package com.example.foxtrotdatabases.Matches;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

import java.util.List;

public class MatchSceneFx {
    MatchesController matchesController = new MatchesController();
    public MatchSceneFx(){
    }
    public Scene addToMatchScene(Button button){
        List<Matches> matchesList = matchesController.getAllMatches();
        //TableView<Matches> matchesTableView = (TableView<Matches>) matchesList;

        HBox hBox = new HBox(button, (Node)matchesList);
        Scene scene = new Scene(hBox, 900, 700);
        return scene;
    }
}

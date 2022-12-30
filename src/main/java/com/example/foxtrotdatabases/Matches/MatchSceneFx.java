package com.example.foxtrotdatabases.Matches;

import com.example.foxtrotdatabases.Start;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 *  Author: Bastian
 */

public class MatchSceneFx {

    /*-------------------------------------------------------------------------------------------------------------
     * Variabler
     -------------------------------------------------------------------------------------------------------------*/
    BorderPane borderPane = new BorderPane();
    MatchesMenu matchesMenu = new MatchesMenu(borderPane);
    Start start = new Start();
    Button teamButton = start.createButtonForMenu("Team Matches");
    Button playerButton = start.createButtonForMenu("Player Matches");
    Button backToStart;


    /*-----------------------------------------------------------------------------------------------------------
     * Konstruktor
     ------------------------------------------------------------------------------------------------------------*/
    public MatchSceneFx() {
    }


    /*---------------------------------------------------------------------------------------------------------
     * Metoder
     ----------------------------------------------------------------------------------------------------------*/

    public Scene addToMatchScene(Button button) {
        this.backToStart = button;

        HBox hBox = new HBox(matchTypeButton());
        borderPane.setTop(hBox);
        Scene scene = new Scene(borderPane, 1000, 700);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("Application.css").toExternalForm());

        return scene;
    }

    public HBox matchTypeButton(){
        HBox hBox = new HBox();

        teamButton.setOnAction(e -> chooseMatchType(0));
        teamButton.setOnMouseClicked(e -> changeTeamFont());
        teamButton.setPadding(new Insets(0, 160, 0, 240));


        playerButton.setOnAction(e -> chooseMatchType(1));
        playerButton.setOnMouseClicked(e -> changePlayerFont());
        playerButton.setPadding(new Insets(0, 150, 0, 10));



        hBox.getChildren().addAll(teamButton, playerButton);
        return hBox;
    }


    public void changeTeamFont(){
        playerButton.setStyle("-fx-font-weight: 400");
        teamButton.setStyle("-fx-font-weight: Bold");
    }
    public void changePlayerFont(){
       teamButton.setStyle("-fx-font-weight: 400");
       playerButton.setStyle("-fx-font-weight: Bold");
    }
    public void chooseMatchType(int matchType){
        matchesMenu.setMatchTypeController(matchType);
        VBox vBox = new VBox();
        vBox.getChildren().add(backToStart);
        vBox.getChildren().addAll(matchesMenu.showMenu());
        borderPane.setLeft(vBox);
        Text text1 = new Text(" ");
        Text text2 = new Text(" ");
        borderPane.setRight(text1);
        borderPane.setCenter(text2);
    }


}
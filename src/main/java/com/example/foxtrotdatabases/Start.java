package com.example.foxtrotdatabases;

import com.example.foxtrotdatabases.Matches.MatchSceneFx;
import com.example.foxtrotdatabases.Players.PlayerSceneFx;
import com.example.foxtrotdatabases.Teams.TeamSceneFx;
import com.example.foxtrotdatabases.Games.GameSceneFx;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.SQLException;


public class Start extends Application {
    BorderPane layout = new BorderPane();
    Button player, teams, games, matches;
    Scene startScene, sceneForPlayer, sceneForTeam, sceneForGame, sceneForMatches;
    Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        //Samma meny fast med vanliga Buttons
        player = createButtonForMenu("Players");
        player.setOnAction(e -> stage.setScene(sceneForPlayer));

        teams = createButtonForMenu("Teams");
        teams.setOnAction(e -> stage.setScene(sceneForTeam));

        //Kodraden nedan är ett exempel på hur vi kan göra så att man behöver logga in innan man kan göra ändringar
        //teams1.setDisable(true);


        games = createButtonForMenu("Games");
        games.setOnAction(e -> stage.setScene(sceneForGame));

        matches = createButtonForMenu("Matches");
        matches.setOnAction(e -> stage.setScene(sceneForMatches));

        //Void metoder som initierar klassvariabler
        createLayout();
        createOtherScenes();

        //Skapar en scene genom en metod som returnerar en scene
        startScene = createStartScene();
        stage.setScene(startScene);
        stage.setTitle("Tournament");
        stage.show();

    }


    //Samma menyknapp fast med Button istället
    public Button createButtonForMenu(String title){
        Button menuButton = new Button(title);
        menuButton.setMinSize(90, 30);
        menuButton.getStyleClass().add("button");
        menuButton.setOnMouseEntered(e -> menuButton.getStyleClass().add("buttonWhenHovering"));
        menuButton.setOnMouseExited(e -> menuButton.getStyleClass().remove("buttonWhenHovering"));
        menuButton.setPadding(new Insets(20, 60, 20, 60));
        return menuButton;
    }


    //lägger till buttons i en HBox, sätter utrymmet mellan menyrutorna och returnerar den färdiga HBoxen
    public HBox createMenuHBox(Button players, Button teams, Button games, Button matches){
        HBox MenuHBox = new HBox(players, teams, games, matches);
        MenuHBox.setSpacing(30);
        return MenuHBox;
    }


    public void createLayout(){
        /*I den övre delen av borderPane kallas en metod som tar in Buttons, placerar ut dem i en HBox
         * och läggs sedan in i den övre delen av layouten.
         */
        layout.setTop(createMenuHBox(player, teams, games, matches));
    }


    //Denna metod lägger till layouten i scene och lägger till Application.css som innehåller information om font size m.m.
    public Scene createStartScene(){
        Scene scene = new Scene(layout, 900, 700);

        try {
            scene.getStylesheets().add(getClass().getClassLoader().getResource("Application.css").toExternalForm());
        }catch(Exception e){
            e.printStackTrace();
        }
        return scene;
    }

    public void createOtherScenes(){
        //Hanterar scene för de olika menyvalen i en metod från en annan klass,
        //i metoden behöver man lägga till en knapp som tar en tillbaka till startrutan
        TeamSceneFx teamSceneFx = new TeamSceneFx();
        sceneForTeam = teamSceneFx.addToTeamScene(createBackToStartButton());

        PlayerSceneFx playerSceneFx = new PlayerSceneFx();
        sceneForPlayer = playerSceneFx.addToPlayerScene(createBackToStartButton());

        GameSceneFx gameSceneFx = new GameSceneFx();
        sceneForGame = gameSceneFx.addToGamesScene(createBackToStartButton());

        MatchSceneFx matchSceneFx = new MatchSceneFx();
        sceneForMatches = matchSceneFx.addToMatchScene(createBackToStartButton());


        //Ger samma information för font m.m. som framsidan hade
        try {
            sceneForPlayer.getStylesheets().add(getClass().getClassLoader().getResource("PlayerStyle.css").toExternalForm());
            //sceneForTeam.getStylesheets().add(getClass().getClassLoader().getResource("Application.css").toExternalForm());
            //sceneForGame.getStylesheets().add(getClass().getClassLoader().getResource("GamesStyle.css").toExternalForm());
            sceneForMatches.getStylesheets().add(getClass().getClassLoader().getResource("Application.css").toExternalForm());
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    //Här skapas alla knappar som leder tillbaka till startrutan
    public Button createBackToStartButton(){
        Button backToStart = createButtonForMenu("Back to Start");
        backToStart.setOnAction(e -> stage.setScene(startScene));
        return backToStart;
    }


    public static void main(String[] args) throws SQLException {
        launch();
    }

}

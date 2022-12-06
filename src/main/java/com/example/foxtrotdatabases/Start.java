package com.example.foxtrotdatabases;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Start extends Application {
    BorderPane layout = new BorderPane();
    Button player1, teams1, games1, matches1;
    Scene startScene, sceneForPlayer, sceneForTeam, sceneForGame, sceneForMatches;
    Stage stage;

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;
        /*Skapar MenuButtons genom en metod som är skriven längre ner
        MenuButton player = createMenuButton("Players");
        MenuButton teams = createMenuButton("Teams");
        MenuButton games = createMenuButton("Games");
        MenuButton matches = createMenuButton("Matches");*/

        //Samma meny fast med vanliga Buttons
        player1 = createButtonForMenu("Players");
        player1.setOnAction(e -> stage.setScene(sceneForPlayer));

        teams1 = createButtonForMenu("Teams");
        teams1.setOnAction(e -> stage.setScene(sceneForTeam));

        //Kodraden nedan är ett exempel på hur vi kan göra så att man behöver logga in innan man kan göra ändringar
        //teams1.setDisable(true);

        games1 = createButtonForMenu("Games");
        games1.setOnAction(e -> stage.setScene(sceneForGame));

        matches1 = createButtonForMenu("Matches");
        matches1.setOnAction(e -> stage.setScene(sceneForMatches));

        //Void metoder som initierar klassvariabler
        createLayout();
        createOtherScenes();

        //Skapar en scene genom en metod som returnerar en scene
        startScene = createStartScene();
        stage.setScene(startScene);
        stage.setTitle("Tournament");
        stage.show();

    }


    /*Skapar MenuButtons med en inparameter för titel. Sedan sätter storlek, style, padding och två val i drop down menyn
     * Drop down menyn är ett exempel och är inget vi behöver använda. Det går snabbt att byta ut allt till vanliga knappar
     */
    public MenuButton createMenuButton(String title){
        MenuButton menuButton = new MenuButton(title);
        menuButton.setMinSize(90, 30);
        menuButton.getStyleClass().add("menuButton");
        menuButton.setPadding(new Insets(20, 40, 20, 40));
        MenuItem menuItem = new MenuItem("Exempel");
        MenuItem menuItem2 = new MenuItem("Exempel2");
        menuButton.getItems().addAll(menuItem, menuItem2);

        return menuButton;
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



    //Tar in MenuButtons, lägger till dem i en HBox, sätter utrymmet mellan menyrutorna och returnerar den färdiga HBoxen
    public HBox createMenuHBox(MenuButton players, MenuButton teams, MenuButton games, MenuButton matches){
        HBox MenuHBox = new HBox(players, teams, games, matches);
        MenuHBox.setSpacing(30);
        return MenuHBox;
    }


    //lägger till buttons i en HBox, sätter utrymmet mellan menyrutorna och returnerar den färdiga HBoxen
    public HBox createMenuHBox(Button players, Button teams, Button games, Button matches){
        HBox MenuHBox = new HBox(players, teams, games, matches);
        MenuHBox.setSpacing(30);
        return MenuHBox;
    }


    public void createLayout(){
        /*I den övre delen av borderPane kallas en metod som tar in MenuItems/Buttons, placerar ut dem i en HBox
         * och läggs sedan in i den övre delen av layouten.
         */
        layout.setTop(createMenuHBox(player1, teams1, games1, matches1));
        //layout.setTop(createMenuHBox(player, teams, games, matches));
    }


    //Denna metod lägger till layouten i scene och lägger till Application.css som innehåller information om font size m.m.
    public Scene createStartScene(){
        Scene scene = new Scene(layout, 900, 700);

        try {
            scene.getStylesheets().add(getClass().getClassLoader().getResource("Application.css").toExternalForm());
        }catch(Exception e){
            System.out.println(e);
        }
        return scene;
    }

    public void createOtherScenes(){
        //Hanterar scene för de olika menyvalen i en metod från en annan klass,
        //i metoden behöver man lägga till en knapp som tar en tillbaka till startrutan
        PlayerSceneFx playerSceneFx = new PlayerSceneFx();
        sceneForPlayer = playerSceneFx.addToPlayerScene(createBackToStartButton());

        TeamSceneFx teamSceneFx = new TeamSceneFx();
        sceneForTeam = teamSceneFx.addToTeamScene(createBackToStartButton());

        GameSceneFx gameSceneFx = new GameSceneFx();
        sceneForGame = gameSceneFx.addToGameScene(createBackToStartButton());

        MatchSceneFx matchSceneFx = new MatchSceneFx();
        sceneForMatches = matchSceneFx.addToMatchScene(createBackToStartButton());


        //Ger samma information för font m.m. som framsidan hade
        try {
            sceneForPlayer.getStylesheets().add(getClass().getClassLoader().getResource("Application.css").toExternalForm());
            sceneForTeam.getStylesheets().add(getClass().getClassLoader().getResource("Application.css").toExternalForm());
            sceneForGame.getStylesheets().add(getClass().getClassLoader().getResource("Application.css").toExternalForm());
            sceneForMatches.getStylesheets().add(getClass().getClassLoader().getResource("Application.css").toExternalForm());
        }catch(Exception e){
            System.out.println(e);
        }

    }

    //Här skapas alla knappar som leder tillbaka till startrutan
    public Button createBackToStartButton(){
        Button backToStart = createButtonForMenu("Back to Start");
        backToStart.setOnAction(e -> stage.setScene(startScene));
        return backToStart;
    }

    public static void main(String[] args){launch();}
}

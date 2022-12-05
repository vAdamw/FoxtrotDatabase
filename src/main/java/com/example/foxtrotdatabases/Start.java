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

    @Override
    public void start(Stage stage) throws Exception {

        //Skapar MenuButtons genom en metod som är skriven längre ner
        MenuButton player = createMenuButton("Players");
        MenuButton teams = createMenuButton("Teams");
        MenuButton games = createMenuButton("Games");
        MenuButton matches = createMenuButton("Matches");

        //Samma meny fast med vanliga Buttons
        Button player1 = createButtonForMenu("Players");
        Button teams1 = createButtonForMenu("Teams");
        Button games1 = createButtonForMenu("Games");
        Button matches1 = createButtonForMenu("Matches");

        /*I den övre delen av bordePane kallas en metod som tar in MenuItems, placerar ut dem i en HBox
         * och returnerar sedan HBoxen. Det läggs alltså till en färdig HBox i den övre delen av layouten
        */
        layout.setTop(createMenuHBox(player1, teams1, games1, matches1));
        //layout.setTop(createMenuHBox(player, teams, games, matches));

        //Skapar en scene genom en metod som returnerar en scene
        stage.setScene(createStartScene());
        stage.setTitle("Tournament");
        stage.setMinHeight(700);
        stage.setMinWidth(900);
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


    //Med vanliga Buttons istället
    public HBox createMenuHBox(Button players, Button teams, Button games, Button matches){
        HBox MenuHBox = new HBox(players, teams, games, matches);
        MenuHBox.setSpacing(30);
        return MenuHBox;
    }



    //Denna metod lägger till layouten i scene och lägger till Application.css som innehåller information om font size m.m.
    public Scene createStartScene(){
        Scene scene = new Scene(layout);

        try {
            scene.getStylesheets().add(getClass().getClassLoader().getResource("Application.css").toExternalForm());
        }catch(Exception e){
            System.out.println(e);
        }
        return scene;
    }



    public static void main(String[] args){launch();}
}

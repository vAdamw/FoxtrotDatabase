package com.example.foxtrotdatabases.Games;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
public class GamesSceneFX {
    public TableColumn<Games, Integer> gamesIdColumn = new TableColumn<Games, Integer>("Games ID");
    public TableColumn<Games, Integer> gamesNameColumn = new TableColumn<Games, Integer>("Games Name");
    BorderPane gamesBoarderPane = new BorderPane();
    GamesController gamesController = new GamesController();
    public GamesSceneFX(){
    }
    public Scene addToGamesScene(Button button){
        HBox backButton = new HBox(button);

        gamesBoarderPane.setCenter(createGamesTable());
        gamesBoarderPane.setTop(backButton);
        gamesBoarderPane.setRight(gameInput());

        HBox hBox = new HBox(button);

        //        scene.getStylesheets().add(getClass().getClassLoader().getResource("GameSceneFX.css").toExternalForm());
        return new Scene(hBox, 900, 700);
    }
    public VBox gameInput() {
        Label gameName1b1 = new Label("Game Name");
        TextField gameNameInput = new TextField();

        gameNameInput.setPromptText("Enter game name: ");
        gameNameInput.setMinWidth(100);
        gameName1b1.setLabelFor(gameNameInput);

        Label gameId1b1 = new Label("Game ID");
        TextField gameIdInput = new TextField();

        gameIdInput.setPromptText("Enter game ID: ");
        gameIdInput.setMinWidth(100);
        gameId1b1.setLabelFor(gameNameInput);

        Button addButton = new Button("Add");
        addButton.setOnAction(e ->addsGame(gameNameInput, gameIdInput));

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e ->deleteGame(gameIdInput));

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e ->updateGame(gameNameInput,gameIdInput));

        VBox gamesInput = new VBox(gameName1b1, gameNameInput, gameId1b1, gameIdInput,addButton,deleteButton,updateButton);
        gamesInput.setPadding(new Insets(100, 0, 0, 150));
        gamesInput.setSpacing(5);

        return gamesInput;
    }
    public void updateGame(TextField gameNameInputTextField, TextField gameIdInputTextField) {
        Games gamesToUpdate = new Games(Integer.parseInt(gameIdInputTextField.getText()), gameNameInputTextField.getText());
        gamesController.updateGames(gamesToUpdate);
        gamesBoarderPane.setCenter(createGamesTable());
    }
    public void deleteGame(TextField gameIdInputTextField) {
        gamesController.deleteGames(Integer.parseInt(gameIdInputTextField.getText()));
        gamesBoarderPane.setCenter(createGamesTable());
    }
    public void addsGame(TextField gameNameInputTextField, TextField gameIdInputTextField) {
        Games gamesToAdd = new Games(Integer.parseInt(gameIdInputTextField.getText()), gameNameInputTextField.getText());
        gamesController.addGame(gamesToAdd);
        gamesBoarderPane.setCenter(createGamesTable());
    }
    public ObservableList<Games> getGames(){

        ObservableList<Games> observableGames = FXCollections.observableArrayList();
        GamesController gamesController = new GamesController();
        observableGames.addAll(gamesController.getAllGames());

        return observableGames;
    }
    public TableView<Games> createGamesTable() {
        TableView gamesTable = new TableView<>();

        gamesIdColumn.setMinWidth(100);
        gamesIdColumn.setCellValueFactory(new PropertyValueFactory<Games, Integer>("gameId"));

        gamesNameColumn.setMinWidth(100);
        gamesNameColumn.setCellValueFactory(new PropertyValueFactory<Games, Integer>("gameName"));

        gamesTable.getColumns().addAll(gamesIdColumn, gamesNameColumn);
        gamesTable.getItems().setAll(getGames());

        return gamesTable;
    }
}

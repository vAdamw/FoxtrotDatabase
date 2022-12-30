package com.example.foxtrotdatabases.Teams;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TeamSceneFx {

    //skapa kolumner
    private TableColumn<Teams, Integer> teamIdColumn = new TableColumn<>("Team ID");
    private TableColumn<Teams, String> teamNameColumn = new TableColumn<>("Team Name");
    private TableColumn<Teams, Integer> gameIdColumn = new TableColumn<>("Game ID");

    BorderPane teamsBorderPane = new BorderPane();
    TeamController teamController = new TeamController();

    public TeamSceneFx() {
    }

    public Scene addToTeamScene(Button button) {
        HBox backButton = new HBox(button);
        backButton.setPadding(new Insets(0, 0, 0, 500));

        teamsBorderPane.setLeft(createTeamsTable());
        teamsBorderPane.setTop(backButton);
        teamsBorderPane.setRight(teamInput());

        HBox hBox = new HBox(teamsBorderPane);
        Scene scene = new Scene(hBox, 900, 700);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("TeamSceneFX.css").toExternalForm());

        return scene;
    }

    //inmatning
    public VBox teamInput() {
        Label teamNamelbl = new Label("Team Name");
        TextField teamNameInput = new TextField();

        teamNameInput.setPromptText("Enter Team Name");
        teamNameInput.setMinWidth(100);
        teamNamelbl.setLabelFor(teamNameInput);

        Label gameIdlbl = new Label("Game ID");
        TextField gameIdInput = new TextField();

        gameIdInput.setPromptText("Enter Game ID");
        gameIdInput.setMinWidth(100);
        gameIdlbl.setLabelFor(gameIdInput);

        Label teamIdlbl = new Label("Team ID");
        TextField teamIdInput = new TextField();

        teamIdInput.setPromptText("Enter Team ID");
        teamIdInput.setMinWidth(100);
        teamIdlbl.setLabelFor(teamIdInput);

        Button addButton = new Button("Add");
        addButton.setMinSize(70, 30);
        addButton.setOnAction(e -> addsTeam(teamNameInput, gameIdInput));

        Button deleteButton = new Button("Delete");
        deleteButton.setMinSize(70, 30);
        deleteButton.setOnAction(e -> deleteTeam(teamIdInput));

        Button updateButton = new Button("Update");
        updateButton.setMinSize(70, 30);
        updateButton.setOnAction(e -> updateTeam(teamNameInput, gameIdInput, teamIdInput));

        VBox teamsInput = new VBox(teamNamelbl, teamNameInput, gameIdlbl, gameIdInput, teamIdlbl, teamIdInput, addButton, deleteButton, updateButton);
        teamsInput.setPadding(new Insets(100, 0, 0, 150));
        teamsInput.setSpacing(5);

        return teamsInput;
    }

    //metod lägga till lag
    public void addsTeam(TextField teamNameTextField, TextField teamGameIdTextField) {
        Teams teamsToAdd = new Teams(teamNameTextField.getText(), Integer.valueOf(teamGameIdTextField.getText()));
        teamController.addTeam(teamsToAdd);
        teamNameTextField.clear();
        teamGameIdTextField.clear();
        teamsBorderPane.setLeft(createTeamsTable());
    }
    //metod uppdatera lag
    private void updateTeam(TextField teamNameTextField, TextField teamGameIdTextField, TextField teamIdTextfield) {
        Teams teamsToUpdate = new Teams(Integer.valueOf(teamIdTextfield.getText()), teamNameTextField.getText(), Integer.valueOf(teamGameIdTextField.getText()));
        teamController.updateTeams(teamsToUpdate);
        teamIdTextfield.clear();
        teamNameTextField.clear();
        teamGameIdTextField.clear();
        teamsBorderPane.setLeft(createTeamsTable());
    }
    //metod radera lag
    private void deleteTeam(TextField teamIdTextfield) {
        teamController.deleteTeam(Integer.valueOf(teamIdTextfield.getText()));
        teamIdTextfield.clear();
        teamsBorderPane.setLeft(createTeamsTable());
    }


    public ObservableList<Teams> getTeams() {
        ObservableList<Teams> observableTeams = FXCollections.observableArrayList();
        TeamController teamController = new TeamController();
        observableTeams.addAll(teamController.getAllTeams());

        return observableTeams;
    }

    //skapa tabell
    public TableView<Teams> createTeamsTable() {
        TableView teamsTable = new TableView<>();

        teamIdColumn.setMinWidth(100);
        teamIdColumn.setCellValueFactory(new PropertyValueFactory<Teams, Integer>("teamId"));

        teamNameColumn.setMinWidth(100);
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<Teams, String>("teamName"));

        gameIdColumn.setMinWidth(100);
        gameIdColumn.setCellValueFactory(new PropertyValueFactory<Teams, Integer>("gameId"));

        teamsTable.getColumns().addAll(teamIdColumn, teamNameColumn, gameIdColumn);
        teamsTable.getItems().setAll(getTeams());

        return teamsTable;
    }
}
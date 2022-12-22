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

    public TeamSceneFx() {
    }

    public Scene addToTeamScene(Button button) {

        BorderPane teamsBorderPane = new BorderPane();
        HBox backButton = new HBox(button);
        teamsBorderPane.setCenter(createTeamsTable());

        teamsBorderPane.setTop(backButton);
        teamsBorderPane.setRight(teamInput());

        HBox hBox = new HBox(teamsBorderPane);

        Scene scene = new Scene(hBox, 900, 700);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("TeamSceneFX.css").toExternalForm());

        return scene;
    }

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

        Button addTeamButton = new Button("Add");
        Button deleteTeamButton = new Button("Delete");
        Button updateTeamButton = new Button("Update");

        VBox teamsInput = new VBox(teamNamelbl, teamNameInput, gameIdlbl, gameIdInput, addTeamButton, deleteTeamButton, updateTeamButton);
        teamsInput.setPadding(new Insets(100, 0, 0, 150));
        teamsInput.setSpacing(5);

        return teamsInput;

    }

    public ObservableList<Teams> getTeams() {

        ObservableList<Teams> observableTeams = FXCollections.observableArrayList();
        TeamController teamController = new TeamController();
        observableTeams.addAll(teamController.getAllTeams());

        return observableTeams;
    }

    public TableView<Teams> createTeamsTable() {

        TableView teamsTable = new TableView<>();

        TableColumn<Teams, Integer> teamIdColumn = new TableColumn<>("Team ID");
        teamIdColumn.setMinWidth(100);
        teamIdColumn.setCellValueFactory(new PropertyValueFactory<Teams, Integer>("teamId"));

        TableColumn<Teams, String> teamNameColumn = new TableColumn<>("Team Name");
        teamNameColumn.setMinWidth(100);
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<Teams, String>("teamName"));

        TableColumn<Teams, Integer> gameIdColumn = new TableColumn<>("Game ID");
        gameIdColumn.setMinWidth(100);
        gameIdColumn.setCellValueFactory(new PropertyValueFactory<Teams, Integer>("gameId"));

        teamsTable.getItems().addAll(getTeams());

        teamsTable.getColumns().addAll(teamIdColumn, teamNameColumn, gameIdColumn);

        return teamsTable;
    }
}


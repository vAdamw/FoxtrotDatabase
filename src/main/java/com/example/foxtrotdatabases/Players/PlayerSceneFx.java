package com.example.foxtrotdatabases.Players;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;


public class PlayerSceneFx {

    public static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");
    TextField firstNameInput;
    TextField lastNameInput;
    TextField nickNameInput;
    BorderPane playerBorderPane;

    TableView playersTable;



    public PlayerSceneFx() {
    }

    public Scene addToPlayerScene(Button button) {

        playerBorderPane = new BorderPane();

        Button addPlayerButton = new Button("Add player");
        addPlayerButton.setOnAction(e -> playerBorderPane.setCenter(this.inputMenu()));
        Button removePlayerButton = new Button("Remove player");
        //removePlayerButton.setOnAction(e -> );
        Button updatePlayerButton = new Button("Update player");
        //updatePlayerButton.setOnAction(e ->);
        Button backToTableButton = new Button("Back to table");
        backToTableButton.setOnAction(e -> backToTableButtonAction());

        HBox topMenu = new HBox(addPlayerButton, removePlayerButton, updatePlayerButton,backToTableButton);
        VBox centerMenu = new VBox(createPlayersTable());
        HBox buttonBox = new HBox(button);

        playerBorderPane.setTop(topMenu);
        playerBorderPane.setCenter(centerMenu);
        playerBorderPane.setBottom(buttonBox);

        addPlayerButton.setOnAction(e -> playerBorderPane.setCenter(this.inputMenu()));

        buttonBox.setPadding(new Insets(5, 400, 5, 400));
        buttonBox.setSpacing(10);
        topMenu.setPadding(new Insets(10, 300, 10, 300));
        centerMenu.setPadding(new Insets(10, 10, 10, 20));

        topMenu.setAlignment(Pos.CENTER);
        centerMenu.setAlignment(Pos.CENTER);


        HBox hBox = new HBox(playerBorderPane);
        Scene scene = new Scene(hBox, 1000, 700);


        return scene;
    }

    public ObservableList<Players> getPlayers() {
        ObservableList<Players> observablePlayers = FXCollections.observableArrayList();
        PlayerController playerController = new PlayerController();
        observablePlayers.addAll(playerController.getAllPlayers());

        return observablePlayers;
    }

    //public Scene addPlayerScene() {}

    public TableView<Players> createPlayersTable() {

        playersTable = new TableView<>();

        TableColumn<Players, Integer> playerIDColumn = new TableColumn<>("Player ID");
        playerIDColumn.setMinWidth(50);
        playerIDColumn.setCellValueFactory(new PropertyValueFactory<Players, Integer>("playerID"));

        TableColumn<Players, String> playerFirstNameColumn = new TableColumn<>("First Name");
        playerFirstNameColumn.setMinWidth(100);
        playerFirstNameColumn.setCellValueFactory(new PropertyValueFactory<Players, String>("firstName"));

        TableColumn<Players, String> playerLastNameColumn = new TableColumn<>("Last Name");
        playerLastNameColumn.setMinWidth(100);
        playerLastNameColumn.setCellValueFactory(new PropertyValueFactory<Players, String>("lastName"));

        TableColumn<Players, String> playerNickNameColumn = new TableColumn<>("Nickname");
        playerNickNameColumn.setMinWidth(100);
        playerNickNameColumn.setCellValueFactory(new PropertyValueFactory<Players, String>("nickname"));

        TableColumn<Players, String> playerStreetAddressColumn = new TableColumn<>("Street Address");
        playerStreetAddressColumn.setMinWidth(100);
        playerStreetAddressColumn.setCellValueFactory(new PropertyValueFactory<Players, String>("streetAddress"));

        TableColumn<Players, Integer> playerPostalCodeColumn = new TableColumn<>("Postal code");
        playerPostalCodeColumn.setMinWidth(100);
        playerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<Players, Integer>("postalCode"));

        TableColumn<Players, String> playerCountryColumn = new TableColumn<>("Country");
        playerCountryColumn.setMinWidth(100);
        playerCountryColumn.setCellValueFactory(new PropertyValueFactory<Players, String>("country"));

        TableColumn<Players, String> playerEmailColumn = new TableColumn<>("Email");
        playerEmailColumn.setMinWidth(100);
        playerEmailColumn.setCellValueFactory(new PropertyValueFactory<Players, String>("email"));

        TableColumn<Players, Integer> playerTeamIDColumn = new TableColumn<>("Team ID");
        playerTeamIDColumn.setMinWidth(100);
        playerTeamIDColumn.setCellValueFactory(new PropertyValueFactory<Players, Integer>("teamID"));

        playersTable.getItems().addAll(getPlayers());

        playersTable.getColumns().addAll(playerIDColumn, playerFirstNameColumn, playerLastNameColumn, playerNickNameColumn,
                playerStreetAddressColumn, playerPostalCodeColumn, playerCountryColumn, playerEmailColumn);


        return playersTable;
    }

    public VBox inputMenu() {

        firstNameInput = new TextField();
        firstNameInput.setPromptText("First name");
        firstNameInput.setMinWidth(100);

        lastNameInput = new TextField();
        lastNameInput.setPromptText("Last name");
        lastNameInput.setMinWidth(100);

        nickNameInput = new TextField();
        nickNameInput.setPromptText("Nickname");
        nickNameInput.setMinWidth(100);

        Button add = new Button("Add");
        add.setAlignment(Pos.BOTTOM_CENTER);
        add.setOnAction(e -> addButtonAction());

        VBox input = new VBox();
        input.getChildren().addAll(firstNameInput, lastNameInput, nickNameInput, add);
        input.setSpacing(10);
        input.setPadding(new Insets(10,10,10,10));
        input.setAlignment(Pos.CENTER);

        return input;
    }

    public void addButtonAction() {

        PlayerController playerController = new PlayerController();
        Players newPlayer = new Players();

        newPlayer.setFirstName(firstNameInput.getText());
        newPlayer.setLastName(lastNameInput.getText());
        newPlayer.setNickname(nickNameInput.getText());
        createPlayersTable().getItems();
        firstNameInput.clear();
        lastNameInput.clear();
        nickNameInput.clear();

        playerController.addPlayer(newPlayer);
    }

    public void backToTableButtonAction() {
        playerBorderPane.setCenter(createPlayersTable());
    }


    //FIXA DELETE- OCH UPDATEFUNKTIONALITET.
    //KOLLA ATT TABELLEN ÄR KOPPLAD TILL MYSQL.
    //FIXA UPDATE-METODEN I PLAYERSMENU.

   /* public void deletePlayerButtonAction() {
        PlayerController playerController = new PlayerController();
        playerController.deletePlayer();
    }*/

    public void updatePlayerButtonAction() {

    }



    /*Players ska registreras med firstname, lastname och nickname -
      gör registreringsfönster.

      "Add Player"
      "Remove Player"
      "Edit Player"*/

}
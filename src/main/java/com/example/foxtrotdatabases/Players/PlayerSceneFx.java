package com.example.foxtrotdatabases.Players;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class PlayerSceneFx {

    //public static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");
    TextField firstNameInput;
    TextField lastNameInput;
    TextField nickNameInput;

    TextField updatePlayerID;
    TextField updateFirstName;
    TextField updateLastName;
    TextField updateNickName;
    TextField updateStreetAddress;
    TextField updatePostalCode;
    TextField updateCountry;
    TextField updateEmail;
    TextField updateTeamID;

    TextField idInputField;

    BorderPane playerBorderPane;


    TableView playersTable;

    Players thePlayerToUpdate;


    PlayerController playerController;

    ObservableList<Players> observablePlayers;


    public PlayerSceneFx() {
    }

    public Scene addToPlayerScene(Button button) {
        //Skapar vår enda scene, med en borderpane, alla förändringar sker i mitten av borderpanen via setCenter.
        playerBorderPane = new BorderPane();

        //Här lägger vi till våra huvudknappar
        Button addPlayerButton = new Button("Add player");
        addPlayerButton.setOnAction(e -> playerBorderPane.setCenter(this.inputMenu()));
        Button removePlayerButton = new Button("Remove player");
        removePlayerButton.setOnAction(e -> deleteButtonAction());
        Button updatePlayerButton = new Button("Update player");
        updatePlayerButton.setOnAction(e -> updatePlayerButtonAction());
        Button backToTableButton = new Button("Back to table");
        backToTableButton.setOnAction(e -> backToTableButtonAction());

        //Skapar två HBoxar och en VBox att lägga innehåll i, lägger Back-knappen i nedersta HBoxen och de lokala i översta.
        HBox topMenu = new HBox(addPlayerButton, removePlayerButton, updatePlayerButton, backToTableButton);
        VBox centerMenu = new VBox(createPlayersTable());
        HBox buttonBox = new HBox(button);

        //Lägger in HBoxarna och VBoxarna i Borderpanen.
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

    //Ej säker på att ObservableList var nödvändig i detta fall, men det funkar?
    public ObservableList<Players> getPlayers() {
        observablePlayers = FXCollections.observableArrayList();
        playerController = new PlayerController();
        observablePlayers.addAll(playerController.getAllPlayers());

        return observablePlayers;
    }

    //Skapar upp tabellen
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

    //Skapar upp en VBox med Textfields för att lägga till ny spelares för-, efter- och smeknamn.
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
        input.setPadding(new Insets(10, 10, 10, 10));
        input.setAlignment(Pos.CENTER);

        return input;
    }

    //Knappfunktion för att lägga till ny spelare,
    public void addButtonAction() {

        playerController = new PlayerController();
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

    //Knappfunktion för back-to-table-knappen, kallar på metoden createPlayersTable i mitten av borderpanen
    public void backToTableButtonAction() {
        playerBorderPane.setCenter(createPlayersTable());
    }

    //Knappfunktion för delete-knappen, kallar på metoden idinputfordelete i mitten av borderpanen
    public void deleteButtonAction() { playerBorderPane.setCenter(idInputForDelete()); }

    public void updatePlayerButtonAction() { playerBorderPane.setCenter(updatePlayerBox()); }


    //Metoden ifråga, skapar upp ett Textfield för input av ID, parsear sen textsträngen till en
    // int och skickar till deletePlayer-metoden i Playercontroller
    public VBox idInputForDelete() {
        Button deleteForRealButton = new Button("Delete player");
        idInputField = new TextField();
        idInputField.setPromptText("Please give the ID of the player you'd like to remove");


        VBox idVbox = new VBox();
        idVbox.getChildren().addAll(idInputField, deleteForRealButton);

        deleteForRealButton.setOnAction(e -> playerController.deletePlayer(Integer.parseInt(idInputField.getText())));

        return idVbox;
    }



    public VBox updatePlayerBox() {

        updatePlayerID = new TextField();
        updatePlayerID.setPromptText("Please give the ID of the player you would like to edit");
        updateFirstName = new TextField();
        updateFirstName.setPromptText("Update first name");
        updateLastName = new TextField();
        updateLastName.setPromptText("Update last name");
        updateNickName = new TextField();
        updateNickName.setPromptText("Update nickname");
        updateStreetAddress = new TextField();
        updateStreetAddress.setPromptText("Update street address");
        updatePostalCode = new TextField();
        updatePostalCode.setPromptText("Update postal code");
        //Textfield updateCity = new TextField();
        //updateCity.setPromptText("Update city");
        updateCountry = new TextField();
        updateCountry.setPromptText("Update country");
        updateEmail = new TextField();
        updateEmail.setPromptText("Update email");
        updateTeamID = new TextField();
        updateTeamID.setPromptText("Update team ID");


        Button updateFinalPlayerButton = new Button("Update player");
        updateFinalPlayerButton.setOnAction(e -> updatePlayerAction());

        VBox updateBox = new VBox();
        updateBox.getChildren().addAll(updatePlayerID, updateFirstName, updateLastName, updateNickName,
                updateStreetAddress, updatePostalCode, updateCountry, updateEmail, updateTeamID, updateFinalPlayerButton);


        return updateBox;
    }

    public void updatePlayerAction() {

        PlayerController playerController = new PlayerController();
        thePlayerToUpdate = new Players();

        thePlayerToUpdate.setPlayerID(Integer.parseInt(updatePlayerID.getText()));
        thePlayerToUpdate.setFirstName(updateFirstName.getText());
        thePlayerToUpdate.setLastName(updateLastName.getText());
        thePlayerToUpdate.setNickname(updateNickName.getText());
        thePlayerToUpdate.setStreetAddress(updateStreetAddress.getText());
        thePlayerToUpdate.setPostalCode(Integer.parseInt(updatePostalCode.getText()));
        thePlayerToUpdate.setCountry(updateCountry.getText());
        thePlayerToUpdate.setEmail(updateEmail.getText());
        thePlayerToUpdate.setTeamID(Integer.parseInt(updateTeamID.getText()));

        playerController.updatePlayer(thePlayerToUpdate);

    }

}
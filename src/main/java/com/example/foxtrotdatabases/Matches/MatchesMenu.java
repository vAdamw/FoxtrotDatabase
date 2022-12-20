package com.example.foxtrotdatabases.Matches;

import com.example.foxtrotdatabases.Start;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class MatchesMenu {
    private int matchTypeController;
    MatchesController matchesController = new MatchesController();
    BorderPane borderPane;
    Start start = new Start();

    public MatchesMenu() {

    }

    public MatchesMenu(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

     /*----------------------------------------------------------------------------------------------------------------------
     * Gemensamma metoder
     ----------------------------------------------------------------------------------------------------------------------*/


    public List<Button> showMenu() {
        Button button1 = start.createButtonForMenu("Show All Matches");
        Button button2 = start.createButtonForMenu("Add A Match");
        Button button3 = start.createButtonForMenu("Delete A Match");
        Button button4 = start.createButtonForMenu("Update A Match");
        //Button button5 = start.createButtonForMenu("Show Match From ID");

        switch (matchTypeController) {
            case 0:
            button1.setOnAction(e -> borderPane.setCenter(showTeamMatches()));
            button2.setOnAction(e -> borderPane.setCenter(createMatch()));
            button3.setOnAction(e -> borderPane.setCenter(questionDeleteMatch()));
            button4.setOnAction(e -> borderPane.setCenter(updateTeamMatch()));
            //button5.setOnAction(e -> borderPane.setBottom(questionForOneMatch()));
            break;
            case 1:
                button1.setOnAction(e -> borderPane.setCenter(showPlayerMatches()));
                button2.setOnAction(e -> borderPane.setCenter(createMatch()));
                button3.setOnAction(e -> borderPane.setCenter(questionDeleteMatch()));
                button4.setOnAction(e -> borderPane.setCenter(updatePlayerMatch()));
                //button5.setOnAction(e -> borderPane.setBottom(questionForOneMatch()));
                break;
        }
        List<Button> buttonList = new ArrayList<>();
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);

        borderPane.setBottom(searchMatch());

        return buttonList;
    }


    public TextField createPromptTextfield(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setMinSize(30, 40);

        return textField;
    }

    public Button styledButton(String name){
        Button button = new Button(name);
        button.setOnMouseEntered(e -> button.getStyleClass().add("buttonWhenHovering"));
        button.setOnMouseExited(e -> button.getStyleClass().remove("buttonWhenHovering"));
        return button;
    }

    public int textFieldToInt(TextField textField){
        int returnValue = -1;
        String text = textField.getText();
        try {
            returnValue = Integer.parseInt(text);
        }catch(Exception e){
            AlertBox.showAlertBox("Hittar inte '" + text + "'");
        }
        return returnValue;
    }



    public VBox questionDeleteMatch() {
        VBox vBox = new VBox();

        TextField textField = new TextField();
        textField.promptTextProperty().set("ID på match");

        Button button = styledButton("delete");
        button.setPadding(new Insets(20, 60, 20, 0));
        button.setOnAction(e -> deleteMatch(textFieldToInt(textField)));
        switch(matchTypeController){
            case 0:
                vBox.getChildren().add(showTeamMatches());
                break;
            case 1:
                vBox.getChildren().add(showPlayerMatches());
                break;
            default:

        }

        vBox.getChildren().addAll(textField, button);

        return vBox;
    }


    public void deleteMatch(int choice) {
            VBox vBox = new VBox();
            switch (matchTypeController) {
                case 0:
                    matchesController.deleteTeamMatch(choice);
                    vBox.getChildren().add(questionDeleteMatch());
                    borderPane.setCenter(vBox);
                    break;
                case 1:
                    matchesController.deletePlayerMatch(choice);
                    vBox.getChildren().add(questionDeleteMatch());
                    borderPane.setCenter(vBox);
                    break;
            }
    }

    public HBox searchMatch() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(5, 20, 20, 50));

        TextField textField = createPromptTextfield("ID på match");
        textField.setMaxSize(140, 30);


        Button button = styledButton("Search");
        button.setPadding(new Insets(5, 20, 20, 20));
        button.setOnAction(e -> decideMatchType(textFieldToInt(textField)));
        hBox.getChildren().addAll(textField, button);
        return hBox;
    }


    public void decideMatchType(int matchId){
            VBox vBox = new VBox();
            switch (matchTypeController) {
                case 0:
                    vBox.getChildren().add(showTeamMatch(matchId));
                    borderPane.setCenter(vBox);
                    break;
                case 1:
                    vBox.getChildren().add(showPlayerMatch(matchId));
                    borderPane.setCenter(vBox);
                    break;
            }

    }

    public void textFieldToUpdateMatch(int matchId, TextField contender1Id, TextField contender2Id, TextField date, TextField scoreContender1,
                                       TextField scoreContender2, TextField gameId) {
        try {
            switch(matchTypeController){
                case 0:
                    TeamMatches teamMatches = new TeamMatches(matchId, Integer.parseInt(contender1Id.getText()), Integer.parseInt(contender2Id.getText()),
                            date.getText(), Integer.parseInt(scoreContender1.getText()), Integer.parseInt(scoreContender2.getText()), Integer.parseInt(gameId.getText()));
                    matchesController.updateTeamMatch(teamMatches);
                    break;
                case 1:
                    PlayerMatches playerMatches = new PlayerMatches(matchId, Integer.parseInt(contender1Id.getText()), Integer.parseInt(contender2Id.getText()),
                            date.getText(), Integer.parseInt(scoreContender1.getText()), Integer.parseInt(scoreContender2.getText()), Integer.parseInt(gameId.getText()));
                    matchesController.updatePlayerMatch(playerMatches);
            }

        } catch (Exception e) {
            AlertBox.showAlertBox("Felaktig inmatning, försök igen");
        }
    }


    public void textFieldToMatches(TextField contender1Id, TextField contender2Id, TextField date, TextField scoreContender1,
                                       TextField scoreContender2, TextField gameId) {
        try {
            switch (matchTypeController){
                case 0:
                    TeamMatches teamMatches = new TeamMatches(Integer.parseInt(contender1Id.getText()), Integer.parseInt(contender2Id.getText()),
                            date.getText(), Integer.parseInt(scoreContender1.getText()), Integer.parseInt(scoreContender2.getText()), Integer.parseInt(gameId.getText()));

                    matchesController.addTeamMatch(teamMatches);
                    break;
                case 1:
                    PlayerMatches playerMatches = new PlayerMatches(Integer.parseInt(contender1Id.getText()), Integer.parseInt(contender2Id.getText()),
                            date.getText(), Integer.parseInt(scoreContender1.getText()), Integer.parseInt(scoreContender2.getText()), Integer.parseInt(gameId.getText()));
                    matchesController.addPlayerMatch(playerMatches);
                    break;
                default:
                    System.out.println("Fel");
                    break;
            }

        } catch (Exception e) {
            AlertBox.showAlertBox("Felaktig inmatning, försök igen");
        }
    }

    public VBox createMatch() {

        Text text1 = new Text();
        Text text2 = new Text();
        Text text3 = new Text("Date:");
        Text text4 = new Text();
        Text text5 = new Text();
        Text text6 = new Text("Game ID:");
        switch(matchTypeController){
            case 0:
                text1.setText("Team 1 ID:");
                text2.setText("Team 2 ID:");
                text4.setText("Score Team 1:");
                text5.setText("Score Team 2:");
                break;
            case 1:
                text1.setText("Player 1 ID:");
                text2.setText("Player 2 ID:");
                text4.setText("Score Player 1:");
                text5.setText("Score Player 2:");
                break;
        }


        TextField textFieldContender1Id = new TextField();
        TextField textFieldContender2Id = new TextField();
        TextField textFieldDate = new TextField();
        TextField textFieldScoreContender1 = new TextField();
        TextField textFieldScoreContender2 = new TextField();
        TextField textFieldGameId = new TextField();

        Button button = styledButton("Submit");
        button.setPadding(new Insets(20, 60, 20, 0));

        button.setOnAction(e -> textFieldToMatches(textFieldContender1Id, textFieldContender2Id, textFieldDate,
                textFieldScoreContender1, textFieldScoreContender2, textFieldGameId));

        VBox vBox = new VBox(text1, textFieldContender1Id, text2, textFieldContender2Id, text3, textFieldDate, text4, textFieldScoreContender1,
                text5, textFieldScoreContender2, text6, textFieldGameId, button);


        return vBox;
    }

    /*----------------------------------------------------------------------------------------------------------------------
     * Team Matches specifika
     ----------------------------------------------------------------------------------------------------------------------*/

    public VBox showTeamMatches() {
        VBox vBox = new VBox();
        List<TeamMatches> teamMatchesList = matchesController.getAllTeamMatches();

        TableView<TeamMatches> tableView = new TableView<>();
        ObservableList<TeamMatches> allMatches = FXCollections.observableArrayList();
        allMatches.addAll(teamMatchesList);


        TableColumn<TeamMatches, Integer> matchId = new TableColumn<>("Match ID");
        matchId.setCellValueFactory(new PropertyValueFactory<>("matchId"));

        TableColumn<TeamMatches, Integer> team1Id = new TableColumn<>("Team1 ID ");
        team1Id.setCellValueFactory(new PropertyValueFactory<>("team1Id"));

        TableColumn<TeamMatches, Integer> team2Id = new TableColumn<>("team2 ID");
        team2Id.setCellValueFactory(new PropertyValueFactory<>("team2Id"));

        TableColumn<TeamMatches, String> matchDate = new TableColumn<>("Match Date");
        matchDate.setCellValueFactory(new PropertyValueFactory<>("matchDate"));

        TableColumn<TeamMatches, Integer> scoreTeam1 = new TableColumn<>("score Team1");
        scoreTeam1.setCellValueFactory(new PropertyValueFactory<>("scoreTeam1"));

        TableColumn<TeamMatches, Integer> scoreTeam2 = new TableColumn<>("score Team2");
        scoreTeam2.setCellValueFactory(new PropertyValueFactory<>("scoreTeam2"));

        TableColumn<TeamMatches, Integer> gameId = new TableColumn<>("game ID");
        gameId.setCellValueFactory(new PropertyValueFactory<>("gameId"));

        tableView.setItems(allMatches);
        tableView.getColumns().addAll(matchId, team1Id, team2Id, matchDate, scoreTeam1, scoreTeam2, gameId);
        tableView.setEditable(true);

        vBox.getChildren().addAll(tableView);
        return vBox;
    }



    public VBox showTeamMatch(int choice) {

        VBox vBox = new VBox();
        List<TeamMatches> teamMatchesList = new ArrayList<>();
        teamMatchesList.add(matchesController.getTeamMatch(choice));

        TableView<TeamMatches> tableView = new TableView<>();
        ObservableList<TeamMatches> allMatches = FXCollections.observableArrayList();
        allMatches.addAll(teamMatchesList);


        TableColumn<TeamMatches, Integer> matchId = new TableColumn<>("Match ID");
        matchId.setCellValueFactory(new PropertyValueFactory<>("matchId"));

        TableColumn<TeamMatches, Integer> team1Id = new TableColumn<>("Team1 ID ");
        team1Id.setCellValueFactory(new PropertyValueFactory<>("team1Id"));

        TableColumn<TeamMatches, Integer> team2Id = new TableColumn<>("team2 ID");
        team2Id.setCellValueFactory(new PropertyValueFactory<>("team2Id"));

        TableColumn<TeamMatches, String> matchDate = new TableColumn<>("Match Date");
        matchDate.setCellValueFactory(new PropertyValueFactory<>("matchDate"));

        TableColumn<TeamMatches, Integer> scoreTeam1 = new TableColumn<>("score Team1");
        scoreTeam1.setCellValueFactory(new PropertyValueFactory<>("scoreTeam1"));

        TableColumn<TeamMatches, Integer> scoreTeam2 = new TableColumn<>("score Team2");
        scoreTeam2.setCellValueFactory(new PropertyValueFactory<>("scoreTeam2"));

        TableColumn<TeamMatches, Integer> gameId = new TableColumn<>("game ID");
        gameId.setCellValueFactory(new PropertyValueFactory<>("gameId"));

        tableView.setItems(allMatches);
        tableView.getColumns().addAll(matchId, team1Id, team2Id, matchDate, scoreTeam1, scoreTeam2, gameId);
        tableView.setEditable(true);

        vBox.getChildren().addAll(tableView);
        return vBox;
    }


    public VBox showTeamMatchToUpdate(int matchToUpdate) {

        List<TeamMatches> updateList = new ArrayList<>();
        updateList.add(matchesController.getTeamMatch(matchToUpdate));

        Text text1 = new Text("Team 1 ID:");
        Text text2 = new Text("Team 2 ID:");
        Text text3 = new Text("Date:");
        Text text4 = new Text("Score Team 1:");
        Text text5 = new Text("Score Team 2:");
        Text text6 = new Text("Game ID:");
        TextField textFieldTeam1Id = new TextField(Integer.toString(updateList.get(0).getTeam1Id()));
        TextField textFieldTeam2Id = new TextField(Integer.toString(updateList.get(0).getTeam2Id()));
        TextField textFieldDate = new TextField(updateList.get(0).getMatchDate());
        TextField textFieldScoreTeam1 = new TextField(Integer.toString(updateList.get(0).getScoreTeam1()));
        TextField textFieldScoreTeam2 = new TextField(Integer.toString(updateList.get(0).getScoreTeam2()));
        TextField textFieldGameId = new TextField(Integer.toString(updateList.get(0).getGameId()));


        Button button = styledButton("Submit");
        button.setPadding(new Insets(20, 60, 20, 0));
        button.setOnAction(e -> textFieldToUpdateMatch(matchToUpdate, textFieldTeam1Id, textFieldTeam2Id, textFieldDate,
                textFieldScoreTeam1, textFieldScoreTeam2, textFieldGameId));
        //matches = new Matches(textFieldTeam1Id.getText(), team2Id, matchDate, scoreTeam1, scoreTeam2, gameId);

        VBox vBox = new VBox(text1, textFieldTeam1Id, text2, textFieldTeam2Id, text3, textFieldDate, text4, textFieldScoreTeam1,
                text5, textFieldScoreTeam2, text6, textFieldGameId, button);
        return vBox;
    }


    public VBox updateTeamMatch() {
        VBox matchesTableView = showTeamMatches();
        TextField matchToUpdate = createPromptTextfield("Match Att Uppdatera");
        Button matchToUpdateButton = start.createButtonForMenu("Continue");
        matchToUpdateButton.setPadding(new Insets(20, 60, 20, 0));
        matchToUpdateButton.setOnAction(e -> borderPane.setCenter(showTeamMatchToUpdate(
                textFieldToInt(matchToUpdate))
        ));
        VBox vBoxInitial = new VBox(matchesTableView, matchToUpdate, matchToUpdateButton);

        return vBoxInitial;

    }

    /*----------------------------------------------------------------------------------------------------------------------
     * Players Matches specifika
     ----------------------------------------------------------------------------------------------------------------------*/



    public VBox showPlayerMatches() {
        VBox vBox = new VBox();
        List<PlayerMatches> playerMatchesList = matchesController.getAllPlayerMatches();

        TableView<PlayerMatches> tableView = new TableView<>();
        ObservableList<PlayerMatches> allMatches = FXCollections.observableArrayList();
        allMatches.addAll(playerMatchesList);


        TableColumn<PlayerMatches, Integer> matchId = new TableColumn<>("Match ID");
        matchId.setCellValueFactory(new PropertyValueFactory<>("matchId"));

        TableColumn<PlayerMatches, Integer> player1Id = new TableColumn<>("Player1 ID ");
        player1Id.setCellValueFactory(new PropertyValueFactory<>("player1Id"));

        TableColumn<PlayerMatches, Integer> player2Id = new TableColumn<>("Player2 ID");
        player2Id.setCellValueFactory(new PropertyValueFactory<>("player2Id"));

        TableColumn<PlayerMatches, String> matchDate = new TableColumn<>("Match Date");
        matchDate.setCellValueFactory(new PropertyValueFactory<>("matchDate"));

        TableColumn<PlayerMatches, Integer> scorePlayer1 = new TableColumn<>("score Player1");
        scorePlayer1.setCellValueFactory(new PropertyValueFactory<>("scorePlayer1"));

        TableColumn<PlayerMatches, Integer> scorePlayer2 = new TableColumn<>("score Player2");
        scorePlayer2.setCellValueFactory(new PropertyValueFactory<>("scorePlayer2"));

        TableColumn<PlayerMatches, Integer> gameId = new TableColumn<>("game ID");
        gameId.setCellValueFactory(new PropertyValueFactory<>("gameId"));

        tableView.setItems(allMatches);
        tableView.getColumns().addAll(matchId, player1Id, player2Id, matchDate, scorePlayer1, scorePlayer2, gameId);
        tableView.setEditable(true);

        vBox.getChildren().addAll(tableView);
        return vBox;
    }


    public VBox showPlayerMatch(int choice) {

        VBox vBox = new VBox();
        List<PlayerMatches> playerMatchesList = new ArrayList<>();
        playerMatchesList.add(matchesController.getPlayerMatch(choice));

        TableView<PlayerMatches> tableView = new TableView<>();
        ObservableList<PlayerMatches> allMatches = FXCollections.observableArrayList();
        allMatches.addAll(playerMatchesList);


        TableColumn<PlayerMatches, Integer> matchId = new TableColumn<>("Match ID");
        matchId.setCellValueFactory(new PropertyValueFactory<>("matchId"));

        TableColumn<PlayerMatches, Integer> player1Id = new TableColumn<>("Player1 ID ");
        player1Id.setCellValueFactory(new PropertyValueFactory<>("player1Id"));

        TableColumn<PlayerMatches, Integer> player2Id = new TableColumn<>("Player2 ID");
        player2Id.setCellValueFactory(new PropertyValueFactory<>("player2Id"));

        TableColumn<PlayerMatches, String> matchDate = new TableColumn<>("Match Date");
        matchDate.setCellValueFactory(new PropertyValueFactory<>("matchDate"));

        TableColumn<PlayerMatches, Integer> scorePlayer1 = new TableColumn<>("score Player1");
        scorePlayer1.setCellValueFactory(new PropertyValueFactory<>("scorePlayer1"));

        TableColumn<PlayerMatches, Integer> scorePlayer2 = new TableColumn<>("score Player2");
        scorePlayer2.setCellValueFactory(new PropertyValueFactory<>("scorePlayer2"));

        TableColumn<PlayerMatches, Integer> gameId = new TableColumn<>("game ID");
        gameId.setCellValueFactory(new PropertyValueFactory<>("gameId"));

        tableView.setItems(allMatches);
        tableView.getColumns().addAll(matchId, player1Id, player2Id, matchDate, scorePlayer1, scorePlayer2, gameId);
        tableView.setEditable(true);

        vBox.getChildren().addAll(tableView);
        return vBox;
    }


    public VBox showPlayerMatchToUpdate(int matchToUpdate) {

        List<PlayerMatches> updateList = new ArrayList<>();
        updateList.add(matchesController.getPlayerMatch(matchToUpdate));

        Text text1 = new Text("Player 1 ID:");
        Text text2 = new Text("Player 2 ID:");
        Text text3 = new Text("Date:");
        Text text4 = new Text("Score Player 1:");
        Text text5 = new Text("Score Player 2:");
        Text text6 = new Text("Game ID:");
        TextField textFieldPlayer1Id = new TextField(Integer.toString(updateList.get(0).getPlayer1Id()));
        TextField textFieldPlayer2Id = new TextField(Integer.toString(updateList.get(0).getPlayer2Id()));
        TextField textFieldDate = new TextField(updateList.get(0).getMatchDate());
        TextField textFieldScorePlayer1 = new TextField(Integer.toString(updateList.get(0).getScorePlayer1()));
        TextField textFieldScorePlayer2 = new TextField(Integer.toString(updateList.get(0).getScorePlayer2()));
        TextField textFieldGameId = new TextField(Integer.toString(updateList.get(0).getGameId()));


        Button button = styledButton("Submit");
        button.setPadding(new Insets(20, 60, 20, 0));
        button.setOnAction(e -> textFieldToUpdateMatch(matchToUpdate, textFieldPlayer1Id, textFieldPlayer2Id, textFieldDate,
                textFieldScorePlayer1, textFieldScorePlayer2, textFieldGameId));


        VBox vBox = new VBox(text1, textFieldPlayer1Id, text2, textFieldPlayer2Id, text3, textFieldDate, text4, textFieldScorePlayer1,
                text5, textFieldScorePlayer2, text6, textFieldGameId, button);
        return vBox;
    }


    public VBox updatePlayerMatch() {
        VBox matchesTableView = showPlayerMatches();
        TextField matchToUpdate = createPromptTextfield("Match Att Uppdatera");
        Button matchToUpdateButton = styledButton("Continue");
        matchToUpdateButton.setPadding(new Insets(20, 60, 20, 0));
        matchToUpdateButton.setOnAction(e -> borderPane.setCenter(showPlayerMatchToUpdate(
               textFieldToInt(matchToUpdate))
        ));
        VBox vBoxInitial = new VBox(matchesTableView, matchToUpdate, matchToUpdateButton);

        return vBoxInitial;

    }

    public void setMatchTypeController(int matchTypeController) {
        this.matchTypeController = matchTypeController;
    }
}













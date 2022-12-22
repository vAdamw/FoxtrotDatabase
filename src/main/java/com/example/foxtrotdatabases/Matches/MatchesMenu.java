package com.example.foxtrotdatabases.Matches;

import com.example.foxtrotdatabases.Start;
import com.example.foxtrotdatabases.Teams.TeamController;
import com.example.foxtrotdatabases.Teams.Teams;
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
    /*-----------------------------------------------------------------------------------------------------------------------
     * Variabler
     ------------------------------------------------------------------------------------------------------------------------*/
    private int matchTypeController;
    MatchesController matchesController = new MatchesController();
    TeamController teamController = new TeamController();
    BorderPane borderPane;
    Start start = new Start();


    /*-----------------------------------------------------------------------------------------------------------------------
     * Konstruktorer
     -----------------------------------------------------------------------------------------------------------------------*/
    public MatchesMenu() {

    }

    public MatchesMenu(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

     /*----------------------------------------------------------------------------------------------------------------------
     * Gemensamma metoder
     ----------------------------------------------------------------------------------------------------------------------*/


    //Visar den vänstra menyn som har olika metoder beroende på om man valt Team-matches eller Player-matches
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
                break;
            case 1:
                button1.setOnAction(e -> borderPane.setCenter(showPlayerMatches()));
                button2.setOnAction(e -> borderPane.setCenter(createMatch()));
                button3.setOnAction(e -> borderPane.setCenter(questionDeleteMatch()));
                button4.setOnAction(e -> borderPane.setCenter(updatePlayerMatch()));
                break;
        }
        List<Button> buttonList = new ArrayList<>();
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);

        //Lägger till en sökfunktion längst ner
        borderPane.setBottom(searchMatch());

        return buttonList;
    }

    //Metod för en textfield med prompt Text
    public TextField createPromptTextfield(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setMinSize(30, 40);

        return textField;
    }

    //Metoden skapar knappar som används i hela matches menu
    public Button styledButton(String name) {
        Button button = new Button(name);
        button.setOnMouseEntered(e -> button.getStyleClass().add("buttonWhenHovering"));
        button.setOnMouseExited(e -> button.getStyleClass().remove("buttonWhenHovering"));
        return button;
    }

    //Metoden omvandlar textfields till integers och öppnar en notisruta om något gick fel
    public int textFieldToInt(TextField textField) {
        int returnValue = -1;
        String text = textField.getText();
        try {
            returnValue = Integer.parseInt(text);
        } catch (Exception e) {
            AlertBox.showAlertBox("Hittar inte '" + text + "'");
        }
        return returnValue;
    }


    /*Metoden skapar en vBox med en textfield där man kan mata in id på den matchen man vill ta bort.
     *Beroende på vilken av team/player menyerna som är markerade så visas olika outputs
     */
    public VBox questionDeleteMatch() {
        VBox vBox = new VBox();

        TextField textField = new TextField();
        textField.promptTextProperty().set("ID på match");

        Button button = styledButton("delete");
        button.setPadding(new Insets(20, 60, 20, 0));
        button.setOnAction(e -> deleteMatch(textFieldToInt(textField)));
        switch (matchTypeController) {
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


    //Raderar en match beroende på vilken av team/player knapparna som är markerade och visar sedan frågesidan på nytt
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


    /*Skapar en textfield och en knapp med funktion att söka upp en enskild match
      Den befinner sig alltid längst ner i rutan*/
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

    /*Från sökfunktionen visar metoden matchen från team-matches eller player-matches beroende på vilken
      av team/player knapparna som är markerade*/
    public void decideMatchType(int matchId) {
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

    //Tar in textfields och uppdaterar matcher beroende på vilken av team/player knapparna som är markerade
    public void textFieldToUpdateMatch(int matchId, TextField contender1Id, TextField contender2Id, TextField date,
                                       TextField scoreContender1, TextField scoreContender2, TextField gameId) {
        TeamMatches teamMatches;
        PlayerMatches playerMatches;
        try {
            switch (matchTypeController) {
                case 0:
                    //Om ändrar konstruktor beroende på om poäng är inskriven
                    if (scoreContender1.getText().length() > 0 && scoreContender2.getText().length() > 0) {
                        teamMatches = new TeamMatches(matchId, textFieldToInt(contender1Id), textFieldToInt(contender2Id),
                                date.getText(), Integer.parseInt(scoreContender1.getText()), Integer.parseInt(scoreContender2.getText()),
                                Integer.parseInt(gameId.getText()));
                    } else {
                        teamMatches = new TeamMatches(matchId, textFieldToInt(contender1Id), textFieldToInt(contender2Id),
                                date.getText(), Integer.parseInt(gameId.getText()));
                    }
                    //Upptaderar matchen
                    matchesController.updateTeamMatch(teamMatches);
                    break;
                case 1:
                    //Ändrar konstruktor beroende på om poäng är inskriven
                    if (scoreContender1.getText().length() > 0 && scoreContender2.getText().length() > 0) {
                        playerMatches = new PlayerMatches(matchId, Integer.parseInt(contender1Id.getText()),
                                Integer.parseInt(contender2Id.getText()), date.getText(), Integer.parseInt(scoreContender1.getText()),
                                Integer.parseInt(scoreContender2.getText()), Integer.parseInt(gameId.getText()));
                    } else {
                        playerMatches = new PlayerMatches(matchId, Integer.parseInt(contender1Id.getText()),
                                Integer.parseInt(contender2Id.getText()), date.getText(), Integer.parseInt(gameId.getText()));
                    }
                    //Uppdaterar matchen
                    matchesController.updatePlayerMatch(playerMatches);
                    break;
                default:
            }

        } catch (Exception e) {
            //En notisruta om något går fel
            AlertBox.showAlertBox("Felaktig inmatning, försök igen");
        }
    }


    //Tar in textfields och skapar en ny match beroende på vilken av team/player knapparna som är markerade
    public void textFieldToMatches(TextField contender1Id, TextField contender2Id, TextField date, TextField scoreContender1,
                                   TextField scoreContender2, TextField gameId) {
        TeamMatches teamMatches;
        PlayerMatches playerMatches;
        try {
            switch (matchTypeController) {
                case 0:
                    //Om ändrar konstruktor beroende på om poäng är inskriven
                    if (scoreContender1.getText().length() > 0 && scoreContender2.getText().length() > 0) {
                        teamMatches = new TeamMatches(textFieldToInt(contender1Id), textFieldToInt(contender2Id),
                                date.getText(), Integer.parseInt(scoreContender1.getText()), Integer.parseInt(scoreContender2.getText()),
                                Integer.parseInt(gameId.getText()));
                    } else {
                        teamMatches = new TeamMatches(textFieldToInt(contender1Id), textFieldToInt(contender2Id),
                                date.getText(), Integer.parseInt(gameId.getText()));
                    }
                    //Skapar match
                    matchesController.addTeamMatch(teamMatches);
                    break;
                case 1:
                    //Om ändrar konstruktor beroende på om poäng är inskriven
                    if (contender1Id.getText().length() > 0 && scoreContender2.getText().length() > 0) {
                        playerMatches = new PlayerMatches(Integer.parseInt(contender1Id.getText()),
                                Integer.parseInt(contender2Id.getText()), date.getText(), Integer.parseInt(scoreContender1.getText()),
                                Integer.parseInt(scoreContender2.getText()), Integer.parseInt(gameId.getText()));
                    } else {
                        playerMatches = new PlayerMatches(Integer.parseInt(contender1Id.getText()),
                                Integer.parseInt(contender2Id.getText()), date.getText(), Integer.parseInt(gameId.getText()));
                    }
                    //Skapar match
                    matchesController.addPlayerMatch(playerMatches);
                    break;
                default:
            }

        } catch (Exception e) {
            //Visar en notisruta om något gått fel
            AlertBox.showAlertBox("Felaktig inmatning, försök igen");
        }
    }

    //Visar alla lag för att underlätta skapandet av ny match. Skapar textrutor beroende på om team eller player knappen är markerad
    //Skapar en knapp som när den trycks hämtar in alla textfields och skickar vidare dem för att skapa en ny match
    public VBox createMatch() {
        VBox helpTableVBox = new VBox();
        switch (matchTypeController) {
            case 0:
                helpTableVBox.getChildren().add(showAllTeams());
                break;
            case 1:
                //Sätt in liknande för spelare sen
                break;
        }


        Text text1 = new Text();
        Text text2 = new Text();
        Text text3 = new Text("Date:");
        Text text4 = new Text();
        Text text5 = new Text();
        Text text6 = new Text("Game ID:");
        switch (matchTypeController) {
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

        VBox vBox = new VBox(helpTableVBox, text1, textFieldContender1Id, text2, textFieldContender2Id, text3, textFieldDate, text4, textFieldScoreContender1,
                text5, textFieldScoreContender2, text6, textFieldGameId, button);


        return vBox;
    }

    //Visar alla lag, används för att underlätta vid skapandet av nya matcher
    public VBox showAllTeams() {
        TableView<Teams> teamsTableView = new TableView<>();
        List<Teams> teamsList = teamController.getAllTeams();
        ObservableList<Teams> allTeamsObservable = FXCollections.observableArrayList();
        allTeamsObservable.addAll(teamsList);

        TableColumn<Teams, String> teamName = new TableColumn<>("Team Name");
        teamName.setCellValueFactory(new PropertyValueFactory<>("teamName"));

        TableColumn<Teams, Integer> teamId = new TableColumn<>("Team ID");
        teamId.setCellValueFactory(new PropertyValueFactory<>("teamId"));

        teamsTableView.setItems(allTeamsObservable);
        teamsTableView.getColumns().addAll(teamId, teamName);
        borderPane.setCenter(teamsTableView);

        VBox vBox = new VBox(teamsTableView);
        vBox.setMaxSize(700, 150);

        return vBox;
    }

    /*----------------------------------------------------------------------------------------------------------------------
     * Team Matches specifika
     ----------------------------------------------------------------------------------------------------------------------*/

    //Visar alla teams matcher
    public VBox showTeamMatches() {
        VBox vBox = createTeamMatchColumns(matchesController.getAllTeamMatches());
        return vBox;
    }

    //Visa en enskild teams match
    public VBox showTeamMatch(int choice) {
        List<TeamMatches> teamMatchesList = new ArrayList<>();
        teamMatchesList.add(matchesController.getTeamMatch(choice));
        VBox vBox = createTeamMatchColumns(teamMatchesList);
        return vBox;
    }

    //Skapar kolumner och kopplar dem till Teams-klassen
    public VBox createTeamMatchColumns(List<TeamMatches> teamMatchesList) {
        VBox vBox = new VBox();
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

        TableColumn<TeamMatches, String> team1Name = new TableColumn<>("team 1 Name");
        team1Name.setCellValueFactory(new PropertyValueFactory<>("team1Name"));

        TableColumn<TeamMatches, String> team2Name = new TableColumn<>("team 2 Name");
        team2Name.setCellValueFactory(new PropertyValueFactory<>("team2Name"));

        TableColumn<TeamMatches, Boolean> finished = new TableColumn<>("Finished");
        finished.setCellValueFactory(new PropertyValueFactory<>("finished"));

        tableView.setItems(allMatches);
        tableView.getColumns().addAll(matchId, team1Name, scoreTeam1, team2Name, scoreTeam2, matchDate, finished);
        tableView.setEditable(true);

        vBox.getChildren().addAll(tableView);
        return vBox;
    }


    /*Visar alla teams matcher, tar in id för den matchen som ska uppdateras och vid knapptryckning skickas integern som
      skrivits in till nästa metod*/
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

    /*Visar alla lag och respektive id för att underlätta vid uppdatering. Metoden hämtar matchen vid inskickat id och
      lägger till den hämtade informationen i respektive textfield*/
    public VBox showTeamMatchToUpdate(int matchToUpdate) {
        VBox helpTableVBox = new VBox();
        switch (matchTypeController) {
            case 0:
                helpTableVBox.getChildren().add(showAllTeams());
                break;
            case 1:
                //Sätt in liknande för spelare sen
                break;
        }

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

        VBox vBox = new VBox(helpTableVBox, text1, textFieldTeam1Id, text2, textFieldTeam2Id, text3, textFieldDate, text4, textFieldScoreTeam1,
                text5, textFieldScoreTeam2, text6, textFieldGameId, button);
        return vBox;
    }



    /*----------------------------------------------------------------------------------------------------------------------
     * Players Matches specifika
     ----------------------------------------------------------------------------------------------------------------------*/

    //Visar alla player matcher
    public VBox showPlayerMatches() {
        VBox vBox = createPlayerMatchColumns(matchesController.getAllPlayerMatches());
        return vBox;
    }


    //Visar en enskild player match
    public VBox showPlayerMatch(int choice) {
        List<PlayerMatches> playerMatchesList = new ArrayList<>();
        playerMatchesList.add(matchesController.getPlayerMatch(choice));
        VBox vBox = createPlayerMatchColumns(playerMatchesList);
        return vBox;
    }

    //Skapar kolumner och kopplar dem till player-klassen
    public VBox createPlayerMatchColumns(List<PlayerMatches> playerMatchesList) {
        VBox vBox = new VBox();

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

        TableColumn<PlayerMatches, Boolean> finished = new TableColumn<>("Finsihed");
        finished.setCellValueFactory(new PropertyValueFactory<>("finished"));

        tableView.setItems(allMatches);
        tableView.getColumns().addAll(matchId, player1Id, player2Id, matchDate, scorePlayer1, scorePlayer2, gameId, finished);
        tableView.setEditable(true);

        vBox.getChildren().addAll(tableView);
        return vBox;
    }

    /*Visar alla spelare och respektive id för att underlätta vid uppdatering. Metoden hämtar matchen vid inskickat id och
         lägger till den hämtade informationen i respektive textfield*/
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


    /*Visar alla player matcher, tar in id för den matchen som ska uppdateras och vid knapptryckning skickas integern som
     skrivits in till nästa metod*/
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













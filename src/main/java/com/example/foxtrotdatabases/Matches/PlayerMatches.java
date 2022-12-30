package com.example.foxtrotdatabases.Matches;

import com.example.foxtrotdatabases.Players.Players;

import javax.persistence.*;

/**
 *  Author: Bastian
 */
@Entity
@Table(name="player_matches")
public class PlayerMatches {
    @Id
    @Column(name = "match_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchId;

    @Column(name = "player1_id")
    private int player1Id;
    @Column(name = "player2_id")
    private int player2Id;
    @Column(name = "match_date")
    private String matchDate;
    @Column(name = "score_player1")
    private int scorePlayer1;
    @Column(name = "score_player2")
    private int scorePlayer2;
    @Column(name = "game_id")
    private int gameId;
    @Column(name = "finished")
    private boolean finished = false;

    @OneToOne
    @JoinColumn(name = "player1_id", insertable = false, updatable = false)
    private Players player1;

    @OneToOne
    @JoinColumn(name = "player2_id", insertable = false, updatable = false)
    private Players player2;

    @Transient
    String player1Nickname;

    @Transient
    String player2Nickname;

    public PlayerMatches(){
    }
    public PlayerMatches(int player1Id, int player2Id, String matchDate, int scorePlayer1, int scorePlayer2, int gameId){
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.matchDate = matchDate;
        this.scorePlayer1 = scorePlayer1;
        this.scorePlayer2 = scorePlayer2;
        this.gameId = gameId;
        //Om denna konstruktor med inparameter för score använts så är matchen avslutad
        finished = true;
    }

    public PlayerMatches(int player1Id, int player2Id, String matchDate, int gameId){
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.matchDate = matchDate;
        this.gameId = gameId;
    }

    public PlayerMatches(int matchId, int player1Id, int player2Id, String matchDate, int gameId){
        this.matchId = matchId;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.matchDate = matchDate;
        this.gameId = gameId;
    }

    public PlayerMatches(int matchId, int player1Id, int player2Id, String matchDate, int scorePlayer1, int scorePlayer2, int gameId){
        this.matchId = matchId;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.matchDate = matchDate;
        this.scorePlayer1 = scorePlayer1;
        this.scorePlayer2 = scorePlayer2;
        this.gameId = gameId;
        //Om denna konstruktor med inparameter för score använts så är matchen avslutad
        finished = true;
    }

    public String getPlayer1Nickname(){
        return player1.getNickname();
    }
    public void setPlayer1Nickname(String nickname){
        this.player1Nickname = nickname;
    }

    public String getPlayer2Nickname(){
        return player2.getNickname();
    }

    public void setPlayer2Nickname(String nickname){
        this.player2Nickname = nickname;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(int player1Id) {
        this.player1Id = player1Id;
    }

    public int getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(int player2Id) {
        this.player2Id = player2Id;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public void setScorePlayer1(int scorePlayer1) {
        this.scorePlayer1 = scorePlayer1;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }

    public void setScorePlayer2(int scorePlayer2) {
        this.scorePlayer2 = scorePlayer2;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Players getPlayer1() {
        return player1;
    }

    public void setPlayer1(Players player1) {
        this.player1 = player1;
    }

    public Players getPlayer2() {
        return player2;
    }

    public void setPlayer2(Players player2) {
        this.player2 = player2;
    }
}

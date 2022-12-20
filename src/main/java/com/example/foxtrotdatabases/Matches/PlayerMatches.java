package com.example.foxtrotdatabases.Matches;

import javax.persistence.*;

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


    public PlayerMatches(){
    }
    public PlayerMatches(int player1Id, int player2Id, String matchDate, int scorePlayer1, int scorePlayer2, int gameId){
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.matchDate = matchDate;
        this.scorePlayer1 = scorePlayer1;
        this.scorePlayer2 = scorePlayer2;
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
}

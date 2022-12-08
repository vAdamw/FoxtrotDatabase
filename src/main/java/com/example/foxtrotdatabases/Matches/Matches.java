package com.example.foxtrotdatabases.Matches;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "matches")
public class Matches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private int matchId;
    @Column(name = "team1_id")
    private int team1Id;
    @Column(name = "team2_id")
    private int team2Id;
    @Column(name = "date")
    private String date;
    @Column(name = "score_team1")
    private int scoreTeam1;
    @Column(name = "score_team2")
    private int scoreTeam2;
    @Column(name = "game_id")
    private int gameId;

    public Matches(){}

    public Matches(int matchId, int team1Id, int team2Id, String date, int scoreTeam1, int scoreTeam2, int gameId) {
        this.matchId = matchId;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.date = date;
        this.scoreTeam1 = scoreTeam1;
        this.scoreTeam2 = scoreTeam2;
        this.gameId = gameId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(int team1Id) {
        this.team1Id = team1Id;
    }

    public int getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(int team2Id) {
        this.team2Id = team2Id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScoreTeam1() {
        return scoreTeam1;
    }

    public void setScoreTeam1(int scoreTeam1) {
        this.scoreTeam1 = scoreTeam1;
    }

    public int getScoreTeam2() {
        return scoreTeam2;
    }

    public void setScoreTeam2(int scoreTeam2) {
        this.scoreTeam2 = scoreTeam2;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

}

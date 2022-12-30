package com.example.foxtrotdatabases.Matches;

import com.example.foxtrotdatabases.Teams.Teams;

import javax.persistence.*;

@Entity
@Table(name = "team_matches")
public class TeamMatches{
    @Id
    @Column(name = "match_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchId;

    @Column(name = "team1_id")
    private int team1Id;
    @Column(name = "team2_id")
    private int team2Id;
    @Column(name = "match_date")
    private String matchDate;
    @Column(name = "score_team1")
    private int scoreTeam1;
    @Column(name = "score_team2")
    private int scoreTeam2;
    @Column(name = "game_id")
    private int gameId;
    @Column(name = "finished")
    private boolean finished;


    @OneToOne
    @JoinColumn(name = "team1_id", insertable = false, updatable = false)
    private Teams team1;
    @OneToOne
    @JoinColumn(name = "team2_id", insertable = false, updatable = false)
    private Teams team2;
    @Transient
    String team1Name;
    @Transient
    String team2Name;



    public TeamMatches(){}
    public TeamMatches(int team1Id, int team2Id, String matchDate, int scoreTeam1, int scoreTeam2, int gameId) {
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this. matchDate= matchDate;
        this.scoreTeam1 = scoreTeam1;
        this.scoreTeam2 = scoreTeam2;
        this.gameId = gameId;
        //Om denna konstruktor med inparameter för score använts så är matchen avslutad
        finished = true;
    }

    public TeamMatches(int matchId, int team1Id, int team2Id, String matchDate, int scoreTeam1, int scoreTeam2, int gameId) {
        this.matchId = matchId;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.matchDate = matchDate;
        this.scoreTeam1 = scoreTeam1;
        this.scoreTeam2 = scoreTeam2;
        this.gameId = gameId;
        //Om denna konstruktor med inparameter för score använts så är matchen avslutad
        finished = true;
    }
    public TeamMatches(int team1Id, int team2Id, String matchDate, int gameId){
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.matchDate = matchDate;
        this.gameId = gameId;
    }
    public TeamMatches(int matchId, int team1Id, int team2Id, String matchDate, int gameId){
        this.matchId = matchId;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.matchDate = matchDate;
        this.gameId = gameId;
    }

    public String getTeam1Name(){
        return team1.getTeamName();
    }
    public void setTeam1Name(String team1Name){
        this.team1Name = team1Name;
    }
    public String getTeam2Name(){
        return team2.getTeamName();
    }
    public void setTeam2Name(String team2Name){
        this.team2Name = team2Name;
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

    public String getMatchDate() {
        return matchDate;
    }

    public void set(String matchDate) {
        this.matchDate = matchDate;
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

    public Teams getTeam1() {
        return team1;
    }

    public void setTeam1(Teams team1) {
        this.team1 = team1;
    }

    public Teams getTeam2() {
        return team2;
    }

    public void setTeam2(Teams team2) {
        this.team2 = team2;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}

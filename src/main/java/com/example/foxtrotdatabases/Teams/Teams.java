package com.example.foxtrotdatabases.Teams;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.*;

@Entity
@Table(name = "teams")

public class Teams {
    @Id
    @Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teamId;
    @Column(name = "team_name")
    private String teamName;
    @Column(name = "game_id")
    private int gameId;

    public Teams() {
    }

    public Teams(String teamName, int gameId) {
        this.teamName = teamName;
        this.gameId = gameId;
    }

    public Teams(int teamId, String teamName, int gameId) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.gameId = gameId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}

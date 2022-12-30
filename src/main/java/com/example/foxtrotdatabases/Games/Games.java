package com.example.foxtrotdatabases.Games;

import javax.persistence.*;
@Entity
@Table(name = "games")
public class Games{
    @Id
    @Column(name ="game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameId;
    @Column(name ="game_name")
    private String gameName;
    public Games(){
    }
    public Games(String gameName) {
        this.gameName = gameName;
    }
    public Games(int gameId, String gameName) {
        this.gameId = gameId;
        this.gameName = gameName;
    }
    public int getGameId() {
        return gameId;
    }
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    public String getGameName() {
        return gameName;
    }
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}

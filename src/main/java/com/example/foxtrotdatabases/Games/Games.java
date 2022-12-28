package com.example.foxtrotdatabases.Games;

import javax.persistence.*;

@Entity
@Table(name = "Games")

public class Games{
    @Id
    @Column(name ="game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int game_id;
    @Column(name ="game_name")
    private String game_name;
    public Games(){
    }
    public Games(int game_id, String game_name) {
        this.game_id = game_id;
        this.game_name = game_name;
    }
    public int getGame_id() {
        return game_id;
    }
    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }
    public String getGame_name() {
        return game_name;
    }
    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }
}

package com.example.foxtrotdatabases.Players;

import com.example.foxtrotdatabases.Teams.Teams;

import javax.persistence.*;

@Entity
@Table(name = "players")

public class Players {
    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playerID;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "street_address")
    private String streetAddress;
    @Column(name = "postal_code")
    private int postalCode;
    @Column(name = "country")
    private String country;
    @Column(name = "email")
    private String email;
    @Column(name = "team_id")
    private int teamID;

/*
    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Teams team; //mapped by?
*/
    /*@OneToOne
    @JoinColumn(name = "game_id",insertable = false,updatable = false)
    private Games game;*/

    @Transient
    int thePlayerToRemove;

    @Transient
    int thePlayerToUpdate;


    public Players() {
    }

    public Players(int playerID, String firstName, String lastName, String nickname,
                   String streetAddress, int postalCode, String country, String email,
                   int teamID) {
        this.playerID = playerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.country = country;
        this.email = email;
        this.teamID = teamID;
    }

    public Players(String firstName, String lastName, String nickname, String streetAddress,
                   int postalCode, String country, String email, int teamID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.country = country;
        this.email = email;
        this.teamID = teamID;
    }
    public Players(String firstName, String lastName, String nickname, int teamID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.teamID = teamID;
    }

    public int getThePlayerToRemove() {
        return thePlayerToRemove;
    }

    public void setThePlayerToRemove(int thePlayerToRemove) {
        this.thePlayerToRemove = thePlayerToRemove;
    }

    public int getThePlayerToUpdate() {
        return thePlayerToUpdate;
    }

    public void setThePlayerToUpdate(int thePlayerToUpdate) {
        this.thePlayerToUpdate = thePlayerToUpdate;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

/*
    @Override
    public String toString() {
        return "Players{" +
                "playerId=" + playerID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", postalCode=" + postalCode +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", teamID=" + teamID +
                '}';
    }*/
}


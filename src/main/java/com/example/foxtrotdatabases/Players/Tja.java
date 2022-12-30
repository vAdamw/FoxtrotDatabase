package com.example.foxtrotdatabases.Players;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.List;

public class Tja {

    //public static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) throws SQLException {

        //entityManagerFactory = Persistence.createEntityManagerFactory("hibernate");

        PlayersMenu playersMenu = new PlayersMenu();
        playersMenu.input();


    }
}
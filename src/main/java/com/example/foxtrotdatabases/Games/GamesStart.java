package com.example.foxtrotdatabases.Games;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
public class GamesStart {
    public static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");
    public static void main(String[] args) throws SQLException{
        GamesController gamesController = new GamesController();
        GamesMenu gamesMenu= new GamesMenu(gamesController);
        gamesMenu.showGamesMenu();
    }
}

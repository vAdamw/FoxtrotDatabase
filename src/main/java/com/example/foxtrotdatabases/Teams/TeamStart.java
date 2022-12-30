package com.example.foxtrotdatabases.Teams;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class TeamStart {

    public static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

    public static void main(String[] args) throws SQLException {
        TeamController teamController = new TeamController();
        TeamMenu teamMenu = new TeamMenu(teamController);
        teamMenu.showTeamMenu();
    }
}
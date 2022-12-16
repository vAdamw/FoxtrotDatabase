package com.example.foxtrotdatabases.Teams;

import java.util.List;
import java.util.Scanner;

public class TeamMenu {

    TeamController teamController;

    public TeamMenu(TeamController theTeamController) {
        this.teamController = theTeamController;
    }

    public void showTeamMenu() {
        System.out.println("Välj ett alternativ: ");
        System.out.println("0 : Visa alla lag ");
        System.out.println("1 : Lägg till lag ");
        System.out.println("2 : Ta bort ett lag ");
        System.out.println("3 : Uppdatera ett lag ");

        teamMenuInput();
    }

    public void teamMenuInput() {

        Scanner scanner = new Scanner(System.in);
        int teamMenyChoice = scanner.nextInt();

        switch (teamMenyChoice) {
            case 0:
                showAllTeam();
                break;
            case 1:
                addTeam();
                break;
            case 2:
                deleteTeam();
                break;
            case 3:
                updateTeam();
                break;
            default:
                System.out.println("fel inmatning");
        }
    }

    public void showAllTeam() {
        List<Teams> teams = teamController.getAllTeams();
        System.out.println("-------> Visa alla lag <-------");
        for (Teams team : teams) {
            System.out.println("Följande lag registrerade: " + team.getTeamName());
        }
        showTeamMenu();
    }

    public void addTeam() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ange lagets namn");
        String teamName = scanner.nextLine();
        Teams theTeamsToAdd = new Teams(teamName);
        if (teamController.addTeam(theTeamsToAdd)) {
            System.out.println("Teamet är tillagd");
        } else {
            System.out.println("Något gick fel");
        }
        showTeamMenu();
    }

    public void deleteTeam() {
        List<Teams> teams = teamController.getAllTeams();
        System.out.println("-------> Visa alla lag <-------");
        for (Teams team : teams) {
            System.out.println("Ange" + team.getTeamId() + "för att ta bort " + team.getTeamName());
        }
        Scanner scanner = new Scanner(System.in);
        int teamIdToDelete = scanner.nextInt();
        if (teamController.deleteTeam(teamIdToDelete)) {
            System.out.println("Allt gick bra, laget är borttaget");
        } else {
            System.out.println("Något gick fel!");
        }
        showTeamMenu();
    }

    public void updateTeam() {
        List<Teams> teams = teamController.getAllTeams();
        System.out.println("-------> Visa alla lag <-------");
        for (Teams team : teams) {
            System.out.println("Ange" + team.getTeamId() + "för att uppdatera " + team.getTeamName());
        }

        Scanner scanner = new Scanner(System.in);
        int teamIdToUpdate = scanner.nextInt();
        Teams theTeamToUpdate = teamController.getTeamsById(teamIdToUpdate);
        System.out.println("Du har valt att uppdatera: " + theTeamToUpdate.getTeamName());

        Scanner scannerTeamUpdate = new Scanner(System.in);
        System.out.println("Ange lagets namn");
        String teamName = scannerTeamUpdate.nextLine();
        Teams theTeamsToAdd = new Teams(teamName);
        Teams theUpdateTeam = new Teams(teamName);

        if (teamController.updateTeams(theUpdateTeam)) {
            System.out.println("Allt gick bra, laget är uppdaterad");
        } else {
            System.out.println("Något gick fel!");
        }
        showTeamMenu();
    }
}
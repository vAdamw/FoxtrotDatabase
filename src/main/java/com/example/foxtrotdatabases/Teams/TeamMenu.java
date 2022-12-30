package com.example.foxtrotdatabases.Teams;

import java.util.List;
import java.util.Scanner;

public class TeamMenu {

    TeamController teamController;
    Scanner scanner = new Scanner(System.in);
    int teamMenuChoice = 1;
    public TeamMenu(TeamController theTeamController) {
        this.teamController = theTeamController;
    }

    public void showTeamMenu() {
        System.out.println("");
        System.out.println("Välj ett alternativ: ");
        System.out.println("0 : Visa alla lag ");
        System.out.println("1 : Lägg till lag ");
        System.out.println("2 : Ta bort ett lag ");
        System.out.println("3 : Uppdatera ett lag ");
        System.out.println("4 : Avsluta ");
        System.out.print("Ditt val: ");
        teamMenuInput();
    }

    public void teamMenuInput() {

        while (teamMenuChoice != 4) {
            try {
                teamMenuChoice = scanner.nextInt();

                switch (teamMenuChoice) {
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
                    case 4:
                        System.out.print("Avslutar..");
                        System.exit(0);
                    default:
                        System.out.println("fel inmatning");
                }
            } catch (Exception e) {
                System.out.print("Använd endast siffror!\n");
                scanner.nextLine();
            }
            showTeamMenu();
        }
    }

    public void showAllTeam() {
        List<Teams> teams = teamController.getAllTeams();
        System.out.println("----------> Visa alla följande lag <----------");
        for (Teams team : teams) {
            System.out.println("Team ID: " + team.getTeamId() + ", " + "Namn: " + team.getTeamName() + ", " + "Game ID: " + team.getGameId());
        }
        showTeamMenu();
    }

    public void addTeam() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ange lagets namn: ");
        String teamName = scanner.nextLine();

        System.out.print("Ange lagets game id: ");
        int gameId = scanner.nextInt();

        Teams theTeamsToAdd = new Teams(teamName, gameId);

        if (teamController.addTeam(theTeamsToAdd)) {
            System.out.println("Teamet är tillagd");
        } else {
            System.out.println("Något gick fel");
        }
        showTeamMenu();
    }

    public void deleteTeam() {
        List<Teams> teams = teamController.getAllTeams();
        System.out.println("----------> Visa alla lag <----------");
        System.out.println("Ange Team ID för att ta bort ett lag:");
        for (Teams team : teams) {
            System.out.println("Team ID: " + team.getTeamId() + "," + " Namn: " + team.getTeamName() + "," + " Game ID: " + team.getGameId());
        }
        System.out.print("Ditt val: ");
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
        System.out.println("Välj ett lag som du vill uppdatera");
        for (Teams team : teams) {
            System.out.println("Ange " + team.getTeamId() + " för att uppdatera " + "laget " + team.getTeamName() + ", Game ID: " + team.getGameId());
        }
        System.out.print("Ditt val: ");

        Scanner scanner = new Scanner(System.in);
        int teamIdToUpdate = scanner.nextInt();
        Teams theTeamToUpdate = teamController.getTeamsById(teamIdToUpdate);
        System.out.println("Du har valt att uppdatera: " +
                " laget " + theTeamToUpdate.getTeamName() +
                ", Game ID " + theTeamToUpdate.getGameId());

        Scanner scannerTeamUpdate = new Scanner(System.in);

        System.out.print("Ange lagets nya namn: ");
        String teamName = scannerTeamUpdate.nextLine();

        System.out.print("Ange Game ID ");
        String gameIdString = scannerTeamUpdate.nextLine();
        int gameId = Integer.parseInt(gameIdString);

        theTeamToUpdate.setTeamName(teamName);
        theTeamToUpdate.setGameId(gameId);

        if (teamController.updateTeams(theTeamToUpdate)) {
            System.out.println("Allt gick bra, laget är uppdaterad");
        } else {
            System.out.println("Något gick fel!");
        }
        showTeamMenu();
    }
}
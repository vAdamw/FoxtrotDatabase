package com.example.foxtrotdatabases.Games;

import java.util.List;
import java.util.Scanner;

public class GamesMenu {

    GamesController gamesController;
    public GamesMenu(GamesController gamesController) {
        this.gamesController = gamesController;
    }
    public void showGamesMenu() {
        System.out.println("");
        System.out.println("0 : Visa alla games.");
        System.out.println("1 : Lägg till games.");
        System.out.println("2 : Ta bort ett game.");
        System.out.println("3 : Uppdatera existerande spel.");
        gameMenuInput();
    }
    public void gameMenuInput() {
        Scanner scanner = new Scanner(System.in);
        int gameMenuChoice = scanner.nextInt();
        
        switch (gameMenuChoice){
            case 0:
                showAllGames();
                break;
            case 1:
                addGame();
                break;
            case 2:
                deleteGame();
                break;
            case 3:
                updateGame();
                break;
            default:
                System.out.println("Fel inmatning.");
        }
    }

    public void deleteGame() {
        List<Games> games = gamesController.getAllGames();
        System.out.println("----------->Visa alla olika games<------------");
        System.out.println("Ange ID för det game du vill ta bort: ");
        for (Games game : games){
            System.out.println("Game ID: " + game.getGame_id() + "," + "Game Name: " + game.getGame_name());
        }
        System.out.println("Ditt val: ");
        Scanner scanner = new Scanner(System.in);
        int gamesIdToDelete = scanner.nextInt();
        if (gamesController.deleteGames(gamesIdToDelete)){
            System.out.println("Gamet är nu bortttaget.");
        } else {
            System.out.println("Något gick fel.");
        }
        showGamesMenu();
    }

    public void updateGame() {
        List<Games> games = gamesController.getAllGames();
        System.out.println("----------->Visa alla olika games<-----------");
        System.out.println("Välj ett spel att uppdatera.");
        for (Games game : games) {
            System.out.println("Game ID: " + game.getGame_id() + "," + "Game Name: " + game.getGame_name());
        }
        System.out.println("Ditt val:");

        Scanner scanner = new Scanner(System.in);
        int gameIdToUpdate = scanner.nextInt();
        Games theGameToUpdate = gamesController.getGamesById(gameIdToUpdate);
        System.out.println("Du har valt att uppdatera: " + " " +  theGameToUpdate.getGame_name() + " " + theGameToUpdate.getGame_id());

        Scanner scannerGameUpdate = new Scanner(System.in);

        System.out.print("Ange gamets nya namn: ");
        String gameName = scannerGameUpdate.nextLine();

        System.out.print("Ange gamets nya ID.");
        String gamesIdString = scannerGameUpdate.nextLine();
        int gameId = Integer.parseInt(gamesIdString);

        theGameToUpdate.setGame_name(gameName);
        theGameToUpdate.setGame_id(gameId);

        if(gamesController.updateGames(theGameToUpdate)){
            System.out.println("Gamet har uppdaterats.");
        }else {
            System.out.println("Något gick fel.");
        }
        showGamesMenu();
    }

    public void addGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ange vilket spel: ");
        String gameName = scanner.nextLine();
        Games theGameToAdd = new Games();
        if (gamesController.addGame(theGameToAdd)) {
            System.out.println("Gamet är tillagt.");
        } else {
            System.out.println("Något gick fel.");
        }
        showGamesMenu();
    }

    public void showAllGames() {
        List<Games> games = gamesController.getAllGames();
        System.out.println("---------->Visa alla olika games<------------");
        for (Games game : games){
            System.out.println("Game ID: " + game.getGame_id() + "," + "Game Name: " + game.getGame_name());
        }
        showGamesMenu();
    }
}

package com.example.foxtrotdatabases.Players;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Scanner;

public class PlayersMenu{
    PlayerController playerController = new PlayerController();
    Players thePlayerToUpdate = new Players();

    public static EntityManagerFactory entityManagerFactory;

    public void input() {

        System.out.println("Do you want to:");
        System.out.println("0: See all players");
        System.out.println("1 : Register a player");
        System.out.println("2 : Delete a player");
        System.out.println("3 : Update a player's info");
        handleMenuInput();
    }

    public void handleMenuInput() {

        Scanner scanner = new Scanner(System.in);
        int menuChoice = scanner.nextInt();

        switch (menuChoice) {
            case 0:
                List<Players> lista = playerController.getAllPlayers();
                for (Players player: lista) {
                    System.out.println(player.toString());}
                break;

            case 1:
                addPlayer();
                break;

            case 2:
                List<Players> lista2 = playerController.getAllPlayers();
                for (int i = 0; i < lista2.size(); i++) {
                    System.out.println(lista2.get(i).getPlayerID() + "  " + lista2.get(i).getNickname());
                }
                System.out.println("Give the ID of the player you would like to remove");
                int thePlayerID = scanner.nextInt();
                playerController.deletePlayer(thePlayerID);
                break;

            case 3:
                updatingPlayer();
                break;

            default:
                System.out.println("Invalid choice!");
        }

        System.out.println();
        input();
    }

    public void addPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add player's first name");
        String firstName = scanner.nextLine();
        System.out.println("Please add player's last name");
        String lastName = scanner.nextLine();
        System.out.println("Please add player's nickname");
        String nickname = scanner.nextLine();
        System.out.println("Please add player's street address");
        String streetAddress = scanner.nextLine();
        System.out.println("Please add player's postal code");
        int postalCode = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please add player's country");
        String country = scanner.nextLine();
        System.out.println("Please add player's email");
        String email = scanner.nextLine();
        System.out.println("Please add player's teamID");
        int teamID = scanner.nextInt();

        Players player = new Players(firstName,lastName,nickname,streetAddress,postalCode,country,email,teamID);
        playerController.addPlayer(player);

    }


    public void updatePlayerMenu() {
    System.out.println("Do you want to:");
        System.out.println("A: Edit player's first name?");
        System.out.println("B: Edit player's last name?");
        System.out.println("C: Edit player's nickname");
        System.out.println("D: Edit player's street address?");
        System.out.println("E: Edit player's postal code?");
        System.out.println("F: Edit player's country?");
        System.out.println("G: Edit player's email?");
        System.out.println("H: Edit player's team id?");

    }

    public void updatingPlayer() {
        thePlayerToUpdate = new Players();
    Scanner scanner = new Scanner(System.in);
        System.out.println("Type the ID of the player you would like to edit");
    int playerID = scanner.nextInt();
        System.out.println("Please edit player's first name");
    String firstName = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Please edit player's last name");
    String lastName = scanner.nextLine();
        System.out.println("Please edit player's nickname");
    String nickname = scanner.nextLine();
        System.out.println("Please edit player's street address");
    String streetAddress = scanner.nextLine();
        System.out.println("Please edit player's postal code");
    int postalCode = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please edit player's country");
    String country = scanner.nextLine();
        System.out.println("Please edit player's email");
    String email = scanner.nextLine();
        System.out.println("Please edit player's teamID");
    int teamID = scanner.nextInt();

    Players thePlayerToUpdate = new Players(firstName,lastName,nickname,streetAddress,postalCode,country,email,teamID);
        playerController.updatePlayer(thePlayerToUpdate);

    }



    /*public void sendToUpdatePlayer(Players thePlayerToUpdate) {
        playerController.updatePlayer(thePlayerToUpdate); }*/


        /*public Players updatingPlayer(Players thePlayerToUpdate) {
        Scanner scanner = new Scanner(System.in);
        String choiceOfUpdate = scanner.nextLine();

        switch (choiceOfUpdate) {
            case "A":
                System.out.println("Give the ID of the player you would like to edit");
                thePlayerToUpdate.setPlayerID(scanner.nextInt());
            case "B":
                System.out.println("Type the new first name");
                thePlayerToUpdate.setFirstName(scanner.nextLine());
                break;
            case "C":
                System.out.println("Type the new last name");
                thePlayerToUpdate.setLastName(scanner.nextLine());
                break;
            case "D":
                System.out.println("Type the new nickname");
                thePlayerToUpdate.setNickname(scanner.nextLine());
                break;
            case "E":
                System.out.println("Type the new street address");
                thePlayerToUpdate.setStreetAddress(scanner.nextLine());
                ;
                break;
            case "F":
                System.out.println("Type the new postal code");
                thePlayerToUpdate.setPostalCode(scanner.nextInt());
                break;
            case "G":
                System.out.println("Type the new country");
                thePlayerToUpdate.setCountry(scanner.nextLine());
                break;
            case "H":
                System.out.println("Type the new team ID");
                thePlayerToUpdate.setTeamID(scanner.nextInt());
                break;
            default:
                System.out.println("Invalid choice!");

        }

        return thePlayerToUpdate;

    }*/

}


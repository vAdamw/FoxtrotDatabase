package com.example.foxtrotdatabases.Players;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Scanner;

public class PlayersMenu{
    PlayerController playerController = new PlayerController();

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
                    //System.out.println(lista);
                    System.out.println(player.toString());
                    /*System.out.println(lista.get(i).getPlayerId() + "  " + lista.get(i).getFirstName() + "  " + lista.get(i).getLastName() +
                            "  " + lista.get(i).getNickname() + "  " + lista.get(i).getStreetAddress() + "  " + lista.get(i).getPostalCode() +
                            "  " + lista.get(i).getCountry() + "  " + lista.get(i).getEmail() + "  " + lista.get(i).getTeamID());*/
                    //System.out.println(lista.get(i).getCountry() + "  " + lista.get(i).getNickname());
                }
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
                List<Players> lista3 = playerController.getAllPlayers();
                for (int i = 0; i < lista3.size(); i++) {
                    System.out.println(lista3.get(i).getPlayerID() + "  " + lista3.get(i).getNickname());
                }
                System.out.println("Give the ID of the player whose information you'd like to update");
                int thePlayerIDToUpdate = scanner.nextInt();
                Players playerToUpdate = playerController.getPlayerById(thePlayerIDToUpdate);
                updatePlayerMenu();
                playerController.updatePlayer(updatingPlayer(playerToUpdate));
                input();
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


    /*public void getAllPlayers() {
        PlayerController playerController = new PlayerController();
        System.out.println("----These are all the players-----");
        List<Players> playersList = playerController.getAllPlayers();
        for (int i = 0; i < playersList.size(); i++) {
            System.out.println(playersList.get(i).getPlayerID() +
                    playersList.get(i).getFirstName() +
                    playersList.get(i).getLastName() +
                    playersList.get(i).getNickname() +
                    playersList.get(i).getStreetAddress() +
                    playersList.get(i).getCountry() +
                    playersList.get(i).getEmail() +
                    playersList.get(i).getTeamID());

        }
    }*/



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
    public Players updatingPlayer(Players thePlayerToEdit) {
        Scanner scanner = new Scanner(System.in);
        String choiceOfUpdate = scanner.nextLine();

        switch (choiceOfUpdate) {
            case "A":
                System.out.println("Type the new first name");
                thePlayerToEdit.setFirstName(scanner.nextLine());
                break;
            case "B":
                System.out.println("Type the new last name");
                thePlayerToEdit.setLastName(scanner.nextLine());
                break;
            case "C":
                System.out.println("Type the new nickname");
                thePlayerToEdit.setNickname(scanner.nextLine());
                break;
            case "D":
                System.out.println("Type the new street address");
                thePlayerToEdit.setStreetAddress(scanner.nextLine());;
                break;
            case "E":
                System.out.println("Type the new postal code");
                thePlayerToEdit.setPostalCode(scanner.nextInt());
                break;
            case "F":
                System.out.println("Type the new country");
                thePlayerToEdit.setCountry(scanner.nextLine());
                break;
            case "G":
                System.out.println("Type the new team ID");
                thePlayerToEdit.setTeamID(scanner.nextInt());
                break;
            default:
                System.out.println("Invalid choice!");

        }

        return thePlayerToEdit;

    }
}


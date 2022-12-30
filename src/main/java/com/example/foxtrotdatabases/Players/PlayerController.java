package com.example.foxtrotdatabases.Players;


import com.example.foxtrotdatabases.Matches.MatchesController;

import javax.persistence.*;
import java.util.List;

public class PlayerController {

    //public static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");
    EntityManagerFactory ENTITY_MANAGER_FACTORY = MatchesController.ENTITY_MANAGER_FACTORY;

    public List<Players> getAllPlayers() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        List<Players> playerList = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            TypedQuery<Players> allPlayersQuery = entityManager.createQuery("from Players", Players.class);
            playerList = allPlayersQuery.getResultList();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return playerList;
    }

    public boolean addPlayer(Players newPlayer) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        boolean isSuccess = true;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(newPlayer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            isSuccess = false;

        } finally {
            entityManager.close();
        }
        return isSuccess;
    }

    public Players getPlayerById(int theId) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Players player;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            player = entityManager.find(Players.class, theId);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            entityManager.close();
            return null;
        }
        entityManager.close();
        return player;
    }

    public void deletePlayer(int thePlayerID) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Players thePlayerToRemove = entityManager.find(Players.class, thePlayerID);
            // Call remove-method of the EntityManager on the rental-entity passed to the method to remove it
            // from the managed objects.
            entityManager.remove(thePlayerToRemove);
            // Call flush-method of the EntityManager to write changes to the database.
            entityManager.flush();
            // Commit the changes
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        } finally {
            entityManager.close();

        }
    }


    public boolean updatePlayer(Players thePlayer) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        boolean isSuccess = true;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Players thePlayerToUpdate = entityManager.find(Players.class, thePlayer.getPlayerID());
            thePlayerToUpdate.setPlayerID(thePlayerToUpdate.getPlayerID());
            thePlayerToUpdate.setFirstName(thePlayer.getFirstName());
            thePlayerToUpdate.setLastName(thePlayer.getLastName());
            thePlayerToUpdate.setNickname(thePlayer.getNickname());
            thePlayerToUpdate.setStreetAddress(thePlayer.getStreetAddress());
            thePlayerToUpdate.setPostalCode(thePlayer.getPostalCode());
            thePlayerToUpdate.setCountry(thePlayer.getCountry());
            thePlayerToUpdate.setEmail(thePlayer.getEmail());
            thePlayerToUpdate.setTeamID(thePlayer.getTeamID());
            entityManager.merge(thePlayerToUpdate);

            // Commit the changes
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            isSuccess = false;

        } finally {
            entityManager.close();
        }
        return isSuccess;
    }
}

package com.example.foxtrotdatabases.Games;

import javax.persistence.*;
import java.util.List;

public class GamesController {
    EntityManagerFactory entityManagerFactory = GamesStart.ENTITY_MANAGER_FACTORY;

    public GamesController(){
    }

    public List<Games> getAllGames(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        List<Games> gamesList = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            TypedQuery<Games> allGamesQuery = entityManager.createQuery("from Games", Games.class);
            gamesList = allGamesQuery.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return gamesList;
    }

    public Games getGamesById(int theId){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        Games games;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            games = entityManager.find(Games.class, theId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
        entityManager.close();
        return games;
    }

    public boolean deleteGames(int GameId){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        boolean isSuccess = true;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Games theGameToRemove = entityManager.find(Games.class, GameId);
            entityManager.remove(theGameToRemove);
            entityManager.flush();
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
    public boolean otherDeleteGames(int theGameId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        boolean isSuccess = true;

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query deleteTeamQuery = entityManager.createNativeQuery("DELETE from games WHERE games_id = " + theGameId);
            deleteTeamQuery.executeUpdate();
            entityManager.flush();
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
    public boolean updateGames(Games theGame){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        boolean isSuccess = true;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Games theGameToUpdate = entityManager.find(Games.class, theGame.getGame_id());
            theGameToUpdate.setGame_name(theGame.getGame_name());
            entityManager.merge(theGameToUpdate);
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

    public boolean addGame(Games theGameToAdd) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        boolean isSuccess = true;

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(theGameToAdd);
            transaction.commit();
        } catch (Exception e){
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
}


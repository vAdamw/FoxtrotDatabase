package com.example.foxtrotdatabases.Matches;


import javax.persistence.*;
import java.util.List;

public class MatchesController{
    public static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

    /*----------------------------------------------------------------------------------------------------------------------
     * Team matches
     ----------------------------------------------------------------------------------------------------------------------*/

    public List<TeamMatches> getAllTeamMatches(){

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        List<TeamMatches> allMatches = null;

        try{
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            TypedQuery<TeamMatches> allMatchesQuery = entityManager.createQuery("from TeamMatches", TeamMatches.class);
            allMatches = allMatchesQuery.getResultList();

            entityTransaction.commit();
        }catch(Exception e){
            if(entityTransaction != null){
                entityTransaction.rollback();
            }
        }finally{
            entityManager.close();
        }
        return allMatches;
    }

    public void addTeamMatch(TeamMatches theMatch){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            entityManager.merge(theMatch);

            entityTransaction.commit();
        }catch(Exception e){
            if(entityTransaction != null){
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }finally{
            entityManager.close();
        }
    }

    public void updateTeamMatch(TeamMatches theMatch){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            entityManager.merge(theMatch);

            entityTransaction.commit();
        }catch(Exception e){
            if(entityTransaction != null){
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }finally{
            entityManager.close();
        }
    }



    public TeamMatches getTeamMatch(int matchId){

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        TeamMatches teamMatches = null;

        try{
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            teamMatches = entityManager.find(TeamMatches.class, matchId);

            entityTransaction.commit();
        }catch(Exception e){
            if(entityTransaction != null){
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }finally{
            entityManager.close();
        }
        return teamMatches;
    }



    public void deleteTeamMatch(int matchToDelete){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        try{
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            TeamMatches matchToRemove = entityManager.find(TeamMatches.class, matchToDelete);
            entityManager.remove(matchToRemove);

            entityManager.flush();

            entityTransaction.commit();
        }catch(Exception e){
            if(entityTransaction != null){
                entityTransaction.rollback();
            }
        }finally {
            entityManager.close();
        }
    }



    /*----------------------------------------------------------------------------------------------------------------------------
     * Player matches
     -----------------------------------------------------------------------------------------------------------------------------*/

    public List<PlayerMatches> getAllPlayerMatches(){

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        List<PlayerMatches> allMatches = null;

        try{
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            TypedQuery<PlayerMatches> allMatchesQuery = entityManager.createQuery("from PlayerMatches", PlayerMatches.class);
            allMatches = allMatchesQuery.getResultList();

            entityTransaction.commit();
        }catch(Exception e){
            if(entityTransaction != null){
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }finally{
            entityManager.close();
        }
        return allMatches;
    }

    public void addPlayerMatch(PlayerMatches theMatch){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            entityManager.merge(theMatch);

            entityTransaction.commit();
        }catch(Exception e){
            if(entityTransaction != null){
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }finally{
            entityManager.close();
        }
    }

    public void updatePlayerMatch(PlayerMatches theMatch){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            entityManager.merge(theMatch);

            entityTransaction.commit();
        }catch(Exception e){
            if(entityTransaction != null){
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }finally{
            entityManager.close();
        }
    }

    public PlayerMatches getPlayerMatch(int matchId){

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        PlayerMatches teamMatches = null;

        try{
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            teamMatches = entityManager.find(PlayerMatches.class, matchId);

            entityTransaction.commit();
        }catch(Exception e){
            if(entityTransaction != null){
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }finally{
            entityManager.close();
        }
        return teamMatches;
    }



    public void deletePlayerMatch(int matchToDelete){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        try{
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            PlayerMatches matchToRemove = entityManager.find(PlayerMatches.class, matchToDelete);
            entityManager.remove(matchToRemove);

            entityManager.flush();

            entityTransaction.commit();
        }catch(Exception e){
            if(entityTransaction != null){
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
    }




}

package com.example.foxtrotdatabases.Teams;

import com.example.foxtrotdatabases.Matches.MatchesController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.*;
import java.util.List;

public class TeamController {

    EntityManagerFactory entityManagerFactory = MatchesController.ENTITY_MANAGER_FACTORY;

    public TeamController() {
    }

    public List<Teams> getAllTeams() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        List<Teams> teamList = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            TypedQuery<Teams> allTeamsQuery = entityManager.createQuery("from Teams", Teams.class);
            teamList = allTeamsQuery.getResultList();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return teamList;

    }

    public boolean addTeam(Teams theTeams) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        boolean isSuccess = true;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(theTeams);
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

    public Teams getTeamsById(int theId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        Teams teams;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            teams = entityManager.find(Teams.class, theId);
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
        return teams;
    }

    public boolean deleteTeam(int theTeamId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        boolean isSuccess = true;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Teams theTeamToRemove = entityManager.find(Teams.class, theTeamId);

            entityManager.remove(theTeamToRemove);

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

    public boolean otherDeleteTeam(int theTeamId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        boolean isSuccess = true;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query deleteTeamQuery = entityManager.createNativeQuery("DELETE from team WHERE team_id = " + theTeamId);
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

    public boolean updateTeams(Teams theTeam) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        boolean isSuccess = true;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Teams theTeamToUpdate = entityManager.find(Teams.class, theTeam.getTeamId());
            theTeamToUpdate.setTeamName(theTeam.getTeamName());
            theTeamToUpdate.setGameId(theTeam.getGameId());
            entityManager.merge(theTeamToUpdate);
            // Commit the changes
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
}
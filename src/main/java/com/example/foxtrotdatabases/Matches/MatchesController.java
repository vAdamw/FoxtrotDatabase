package com.example.foxtrotdatabases.Matches;


import com.example.foxtrotdatabases.Start;

import javax.persistence.*;
import java.util.List;

public class MatchesController{

    public List<Matches> getAllMatches(){

        EntityManager entityManager = Start.ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        List<Matches> allMatches = null;

        try{
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            TypedQuery<Matches> allMatchesQuery = entityManager.createQuery("from matches", Matches.class);
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


}

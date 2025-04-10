package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.models.Roles;
import org.example.models.User;
import org.example.services.DatabaseService;

import java.util.UUID;

public class UserRepository implements  IUserRepository{
    public final DatabaseService dbService;

    public UserRepository(DatabaseService dbService) {
        this.dbService = dbService;
    }

    @Override
    public User GetUser(String username, String password) {

        EntityManager entityManager = dbService.entityManagerFactory.createEntityManager();
        Query querry = entityManager.createQuery( "select u from User u where username=:name and password=:pass");
        querry.setParameter("name",username);
        querry.setParameter("pass",password);
        try {
            return (User) querry.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
    @Override
    public User GetNewUser(String username,String password) {
        EntityManager entityManager = dbService.entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            User user = new User(username, password);
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return user;
        }catch(Exception e){
            return null;
            //entityManager.getTransaction().rollback();
        }
    }

    @Override
    public Roles GetUserRole(UUID sessionId) {
        EntityManager entityManager = dbService.entityManagerFactory.createEntityManager();
        Query querry = entityManager.createQuery( "select u.role from User u where sessionId=:sessionId");
        querry.setParameter("sessionId",sessionId);
        try {
            return (Roles) querry.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }


}

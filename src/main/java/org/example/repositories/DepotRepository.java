package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.models.Depot;
import org.example.models.Good;
import org.example.services.DatabaseService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepotRepository implements IBaseRepository<Depot>{

    public final DatabaseService dbService;

    public DepotRepository(DatabaseService dbService) {
        this.dbService = dbService;
    }
    @Override
    public List<Depot> GetAll() {
        EntityManager entityManager = dbService.entityManagerFactory.createEntityManager();
        try {
            String queryString = "SELECT t FROM Depot t";
            return entityManager.createQuery(queryString, Depot.class).getResultList();
        }catch (Exception e){
            return null;
        }
        finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public void Add(Depot o) {
        EntityManager entityManager = dbService.entityManagerFactory.createEntityManager();
        if(o.getId()!=null)
            o.setId(null);
        entityManager.getTransaction().begin();
        entityManager.persist(o);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void Update(Depot o) {
        EntityManager entityManager = dbService.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Depot depot = entityManager.find(Depot.class, o.getId());
        if(o.getManager()!=null)
            depot.setManager(o.getManager());
        if(o.getName()!=null)
            depot.setName(o.getName());
        if(o.getSurface()!=null)
            depot.setName(o.getName());
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    @Override
    public void Remove(Integer id) {
        EntityManager entityManager = dbService.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Depot.class,id));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

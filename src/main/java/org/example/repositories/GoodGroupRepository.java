package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.models.Depot;
import org.example.models.Good;
import org.example.models.GoodGroup;
import org.example.services.DatabaseService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoodGroupRepository implements IBaseRepository<GoodGroup> {
    public final DatabaseService dbService;

    public GoodGroupRepository(DatabaseService dbService) {
        this.dbService = dbService;
    }
    @Override
    public List<GoodGroup> GetAll() {
        EntityManager entityManager = dbService.entityManagerFactory.createEntityManager();
        try {
            String queryString = "SELECT t FROM GoodGroup t";
            return entityManager.createQuery(queryString, GoodGroup.class).getResultList();
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
    public void Add(GoodGroup o) {
        EntityManager entityManager = dbService.entityManagerFactory.createEntityManager();
        if(o.getId()!=null)
            o.setId(null);
        Integer id = o.getDepotId();
        entityManager.getTransaction().begin();
        entityManager.persist(o);
        if(id!= null)
        {
            o.setDepot(entityManager.find(Depot.class,id));
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void Update(GoodGroup o) {
        EntityManager entityManager = dbService.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        GoodGroup group = entityManager.find(GoodGroup.class, o.getId());
        if(o.getFood()!=null)
            group.setFood(o.getFood());
        if(o.getName()!=null)
            group.setName(o.getName());
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void Remove(Integer id) {
        EntityManager entityManager = dbService.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(GoodGroup.class,id));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

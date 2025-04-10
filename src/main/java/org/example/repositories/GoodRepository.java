package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.models.Depot;
import org.example.models.Good;
import org.example.models.GoodGroup;
import org.example.services.DatabaseService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoodRepository implements IBaseRepository<Good>{
    public final DatabaseService dbService;

    public GoodRepository(DatabaseService dbService) {
        this.dbService = dbService;
    }
    @Override
    public List<Good> GetAll() {
        EntityManager entityManager = dbService.entityManagerFactory.createEntityManager();
        try {
            String queryString = "SELECT t FROM Good t";
            return entityManager.createQuery(queryString, Good.class).getResultList();
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
    public void Add(Good o) {

        EntityManager entityManager = dbService.entityManagerFactory.createEntityManager();
        if(o.getId()!=null)
            o.setId(null);
        Integer id = o.getGoodGroupId();
        entityManager.getTransaction().begin();
        entityManager.persist(o);
        if(id!= null)
        {
            o.setGoodGroup(entityManager.find(GoodGroup.class,id));
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void Update(Good o) {
        EntityManager entityManager = dbService.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Good good = entityManager.find(Good.class, o.getId());
        if(o.getAmount()!=null)
            good.setAmount(o.getAmount());
        if(o.getName()!=null)
            good.setName(o.getName());
        if(o.getPrice()!=null)
            good.setPrice(o.getPrice());
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void Remove(Integer id) {
        EntityManager entityManager = dbService.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Good.class,id));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

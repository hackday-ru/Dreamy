package org.kedzo.dreamy.services.impl;

import org.kedzo.dreamy.services.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "dreamRepository")
public class DreamRepository implements CrudRepository<Dream> {

    @Autowired
    DreamyEntityManager entityManager;

    @Override
    public long save(Dream entity) {
        entityManager.instance().getTransaction().begin();
        entityManager.instance().persist(entity);
        entityManager.instance().getTransaction().commit();
        return entity.getId();
    }

    @Override
    public Dream load(long id) {
        return entityManager.instance().find(Dream.class, id);
    }

    @Override
    public void delete(Dream entity) {
        entityManager.instance().getTransaction().begin();
        entityManager.instance().remove(entity);
        entityManager.instance().getTransaction().commit();
    }

    @Override
    public long update(Dream entity) {
        return save(entity);
    }
}

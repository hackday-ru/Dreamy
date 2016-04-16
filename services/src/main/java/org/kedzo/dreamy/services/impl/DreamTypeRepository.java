package org.kedzo.dreamy.services.impl;

import org.kedzo.dreamy.models.DreamType;
import org.kedzo.dreamy.services.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by woodman on 16.04.16.
 */
public class DreamTypeRepository implements CrudRepository<DreamType> {


    @Autowired
    DreamyEntityManager entityManager;

    @Override
    public long save(DreamType entity) {
        entityManager.instance().getTransaction().begin();
        entityManager.instance().persist(entity);
        entityManager.instance().getTransaction().commit();
        return entity.getId();
    }

    @Override
    public DreamType load(long id) {
        return entityManager.instance().find(DreamType.class, id);
    }


    @Override
    public void delete(DreamType entity) {
        entityManager.instance().getTransaction().begin();
        entityManager.instance().remove(entity);
        entityManager.instance().getTransaction().commit();
    }

    @Override
    public long update(DreamType entity) {
        return save(entity);
    }

}

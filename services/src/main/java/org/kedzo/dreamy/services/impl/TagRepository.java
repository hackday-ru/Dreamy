package org.kedzo.dreamy.services.impl;

import org.kedzo.dreamy.models.Tag;
import org.kedzo.dreamy.services.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("tagRepository")
public class TagRepository implements CrudRepository<Tag> {
    
    @Autowired
    DreamyEntityManager entityManager;

    @Override
    public long save(Tag entity) {
        entityManager.instance().getTransaction().begin();
        entityManager.instance().persist(entity);
        entityManager.instance().getTransaction().commit();
        return entity.getId();
    }

    @Override
    public Tag load(long id) {
        return entityManager.instance().find(Tag.class, id);
    }

    @Override
    public void delete(Tag entity) {
        entityManager.instance().getTransaction().begin();
        entityManager.instance().remove(entity);
        entityManager.instance().getTransaction().commit();
    }

    @Override
    public long update(Tag entity) {
        return save(entity);
    }
    
}

package org.kedzo.dreamy.services.impl;

import org.kedzo.dreamy.models.Blog;
import org.kedzo.dreamy.services.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("blogRepository")
public class BlogRepository implements CrudRepository<Blog> {

    @Autowired
    DreamyEntityManager entityManager;

    @Override
    public long save(Blog entity) {
        entityManager.instance().getTransaction().begin();
        entityManager.instance().persist(entity);
        entityManager.instance().getTransaction().commit();
        return entity.getId();
    }

    @Override
    public Blog load(long id) {
        return entityManager.instance().find(Blog.class, id);
    }

    @Override
    public void delete(Blog entity) {
        entityManager.instance().getTransaction().begin();
        entityManager.instance().remove(entity);
        entityManager.instance().getTransaction().commit();
    }

    @Override
    public long update(Blog entity) {
        return save(entity);
    }
}

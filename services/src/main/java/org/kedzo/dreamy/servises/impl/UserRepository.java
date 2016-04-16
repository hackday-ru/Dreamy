package org.kedzo.dreamy.servises.impl;

import org.kedzo.dreamy.servises.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "userRepository")
public class UserRepository implements CrudRepository<User> {

    @Autowired
    DreamyEntityManager entityManager;

    @Override
    public long save(User entity) {
        entityManager.instance().getTransaction().begin();
        entityManager.instance().persist(entity);
        entityManager.instance().getTransaction().commit();
        return entity.getId();
    }

    @Override
    public User load(long id) {
        return entityManager.instance().find(User.class, id);
    }

    @Override
    public void delete(User entity) {
        entityManager.instance().getTransaction().begin();
        entityManager.instance().remove(entity);
        entityManager.instance().getTransaction().commit();
    }

    @Override
    public long update(User entity) {
        return save(entity);
    }
}

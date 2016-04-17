package org.kedzo.dreamy.services.impl;

import org.kedzo.dreamy.models.Dream;
import org.kedzo.dreamy.models.Tag;
import org.kedzo.dreamy.models.User;
import org.kedzo.dreamy.services.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Set<Dream> getAllDreams(User user) {
        Query query = entityManager.instance().createNativeQuery(
                String.format("SELECT * FROM dreams WHERE id IN (SELECT dreams_id FROM users_dreams WHERE user_id=%s)",
                        user.getId()),
                Dream.class
        );
        List<Dream> resultList = (List<Dream>) query.getResultList();
        return new HashSet<>(resultList);
    }

    public Integer getNumberUsers() {
        Query query = entityManager.instance().createNativeQuery(
                "SELECT * FROM users", User.class);
        List<User> users = (List<User>) query.getResultList();
        return users.size();
    }
}

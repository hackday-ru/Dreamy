package org.kedzo.dreamy.services.impl;

import org.kedzo.dreamy.models.Dream;
import org.kedzo.dreamy.models.Episode;
import org.kedzo.dreamy.services.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Set<Episode> getAllEpisodes() {
        TypedQuery<Episode> query = entityManager.instance().createQuery("select e from Episode e", Episode.class);
        return query.getResultList().stream().collect(Collectors.toSet());
    }

    public Set<Dream> getAllDreams() {
        TypedQuery<Dream> query = entityManager.instance().createQuery("select d from Dream d", Dream.class);
        return query.getResultList().stream().collect(Collectors.toSet());
    }
}

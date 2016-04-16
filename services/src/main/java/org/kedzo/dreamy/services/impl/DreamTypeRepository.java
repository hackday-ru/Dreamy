package org.kedzo.dreamy.services.impl;

import org.kedzo.dreamy.models.Dream;
import org.kedzo.dreamy.models.DreamType;
import org.kedzo.dreamy.models.User;
import org.kedzo.dreamy.services.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Repository(value = "dreamTypeRepository")
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

    public Set<DreamType> getRandDreamType() {
        Random random = new Random();
        Query query = entityManager.instance().createNativeQuery(
                "SELECT * FROM dream_type ORDER BY rand() LIMIT " + random.nextInt(4),
                DreamType.class
        );
        List<DreamType> resultList = ((List<DreamType>) query.getResultList());
        return new HashSet<>(resultList);
    }

    public Set<DreamType> getAllDreamsTypes(Dream dream) {
        Query query = entityManager.instance().createNativeQuery(
                String.format("SELECT * FROM dream_type WHERE id IN (SELECT types_id FROM dreams_dream_type WHERE Dream_id=%s)",
                        dream.getId()),
                Dream.class
        );
        List<DreamType> resultList = (List<DreamType>) query.getResultList();
        return new HashSet<>(resultList);
    }
}

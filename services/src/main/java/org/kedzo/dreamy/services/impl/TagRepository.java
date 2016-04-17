package org.kedzo.dreamy.services.impl;

import org.kedzo.dreamy.models.Tag;
import org.kedzo.dreamy.services.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Set<Tag> getRandTags() {
        Random random = new Random();
        Query query = entityManager.instance().createNativeQuery(
                "SELECT * FROM tags ORDER BY rand() LIMIT " + random.nextInt(3) + 1,
                Tag.class
        );
        List<Tag> resultList = ((List<Tag>) query.getResultList());
        return new HashSet<>(resultList);
    }

    public Set<Tag> getTagsByTerms(List<String> terms) {
        TypedQuery<Tag> query = entityManager.instance().createQuery("SELECT t from Tag as t where t.term in (:terms)", Tag.class)
                .setParameter("terms", terms);
        return query.getResultList().stream().collect(Collectors.toSet());
    }
}

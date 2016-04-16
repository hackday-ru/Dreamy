package org.kedzo.dreamy.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository(value = "episodeTagsRepository")
public class EpisodeTagsRepository {

    @Autowired
    DreamyEntityManager entityManager;

    public void insert(long episodeId, long tagId) {
        try {
            entityManager.instance().getTransaction().begin();

            Query query = entityManager.instance().createNativeQuery(
                    "INSERT INTO episodes_tags(Episode_id, tags_id) VALUES (:episode, :tag)"
            ).setParameter("episode", episodeId).setParameter("tag", tagId);

            query.executeUpdate();

            entityManager.instance().getTransaction().commit();
        } catch (Exception e) {
            entityManager.instance().getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

}

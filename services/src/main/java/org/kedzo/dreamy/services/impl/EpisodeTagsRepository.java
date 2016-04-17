package org.kedzo.dreamy.services.impl;

import org.kedzo.dreamy.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Repository(value = "episodeTagsRepository")
public class EpisodeTagsRepository {

    @Autowired
    DreamyEntityManager entityManager;

    public void insert(long episodeId, long tagId) {
        try {
            entityManager.instance().getTransaction().begin();

            Query query = entityManager.instance().createNativeQuery(
                    String.format("INSERT INTO episodes_tags(episode_id, tags_id) VALUES (%s, %s)", episodeId, tagId));

            query.executeUpdate();

            entityManager.instance().getTransaction().commit();
        } catch (Exception e) {
            entityManager.instance().getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public Set<Tag> getTagsByEpisodeId(long episodeId) {
        try {

            Query query = entityManager.instance().createNativeQuery(
                    String.format("select * from tags where tags.id in (select tags_id from episodes_tags where episode_id = %s);", episodeId), Tag.class);

            List<Tag> resultList = ((List<Tag>) query.getResultList());
            return new LinkedHashSet<>(resultList);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}

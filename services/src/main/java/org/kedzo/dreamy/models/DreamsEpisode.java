package org.kedzo.dreamy.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by woodman on 16.04.16.
 */
@Entity
@Table(name = "dreams_episodes")
public class DreamsEpisode implements RepositoryEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "dream_id")
    private Dream dream;
    @Column(name = "episode_id")
    private Episode episode;

    public long getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public Dream getDream() {
        return dream;
    }

    public void setDream(Dream dream) {
        this.dream = dream;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }
}

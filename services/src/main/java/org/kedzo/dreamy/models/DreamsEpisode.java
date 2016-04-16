package org.kedzo.dreamy.models;

/**
 * Created by woodman on 16.04.16.
 */
public class DreamsEpisode implements RepositoryEntity {
    private long id;
    private Dream dream;
    private Episode episode;
    private int order;

    public long getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}

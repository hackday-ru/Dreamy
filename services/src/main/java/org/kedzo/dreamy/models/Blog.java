package org.kedzo.dreamy.models;

/**
 * Created by woodman on 16.04.16.
 */
public class Blog implements RepositoryEntity {
    private int id;
    private User user;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

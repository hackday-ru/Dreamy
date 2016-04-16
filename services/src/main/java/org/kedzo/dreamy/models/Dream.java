package org.kedzo.dreamy.models;

import java.util.Date;

/**
 * Created by woodman on 16.04.16.
 */
public class Dream implements RepositoryEntity {
    private long id;
    private User user;
    private Date date;
    private String discription;

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}

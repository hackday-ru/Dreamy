package org.kedzo.dreamy.models;

import javax.persistence.*;

/**
 * Created by woodman on 16.04.16.
 */
@Entity
@Table(name = "blog_comments")
public class BlogComment implements RepositoryEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    @Column(name = "comment")
    private String comment;

    public long getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

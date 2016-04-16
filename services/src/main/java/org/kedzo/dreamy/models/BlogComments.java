package org.kedzo.dreamy.models;

/**
 * Created by woodman on 16.04.16.
 */
public class BlogComments implements RepositoryEntity {
    private long id;
    private BlogEntry blogEntry;
    private User user;
    private String comment;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BlogEntry getBlogEntry() {
        return blogEntry;
    }

    public void setBlogEntry(BlogEntry blogEntry) {
        this.blogEntry = blogEntry;
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

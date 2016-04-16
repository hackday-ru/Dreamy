package org.kedzo.dreamy.models;

import java.util.Date;

/**
 * Created by woodman on 16.04.16.
 */
public class BlogEntry implements RepositoryEntity {
    private long id;
    private Blog blog;
    private Date date;
    private String title;
    private String text;
    private Dream dream;

    public long getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Dream getDream() {
        return dream;
    }

    public void setDream(Dream dream) {
        this.dream = dream;
    }
}

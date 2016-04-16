package org.kedzo.dreamy.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by woodman on 16.04.16.
 */
@Entity
@Table(name = "blog_enties")
public class BlogEntry implements RepositoryEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;
    @Column(name = "title")
    private String title;
    @Column(name = "text")
    private String text;
    @OneToOne(fetch = FetchType.LAZY)
    private Dream dream;
    @OneToMany(fetch = FetchType.LAZY)
    private Set<BlogComment> blogCommentses;

    public long getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
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

    public Set<BlogComment> getBlogCommentses() {
        return blogCommentses;
    }

    public void setBlogCommentses(Set<BlogComment> blogCommentses) {
        this.blogCommentses = blogCommentses;
    }
}

package org.kedzo.dreamy.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Set;

/**
 * Created by woodman on 16.04.16.
 */
public class Blog implements RepositoryEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    private Set<BlogEntry> blogEntries;

    public long getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public Set<BlogEntry> getBlogEntries() {
        return blogEntries;
    }

    public void setBlogEntries(Set<BlogEntry> blogEntries) {
        this.blogEntries = blogEntries;
    }
}

package org.kedzo.dreamy.models;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by woodman on 16.04.16.
 */
@Entity
@Table(name = "blogs")
public class Blog implements RepositoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @OneToMany(fetch = FetchType.LAZY)
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

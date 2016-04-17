package org.kedzo.dreamy.models;

import javax.persistence.*;

/**
 * Created by woodman on 16.04.16.
 */
@Entity
@Table(name = "tags")
public class Tag implements RepositoryEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "term")
    private String term;
    @Column(name = "picture_url")
    private String picture;
    @Column(name = "intepritation")
    private String interpritation;

    public Tag() {
    }

    public Tag(String interpritation) {
        this.interpritation = interpritation;
    }

    public long getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getPcture() {
        return picture;
    }

    public void setPicture(String pcture) {
        this.picture = pcture;
    }

    public String getInterpritation() {
        return interpritation;
    }

    public void setInterpritation(String interpritation) {
        this.interpritation = interpritation;
    }
}

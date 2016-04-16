package org.kedzo.dreamy.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
    @Column(name = "picture")
    private byte[] pcture;
    @Column(name = "intepritation")
    private String interpritation;

    public long getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public byte[] getPcture() {
        return pcture;
    }

    public void setPcture(byte[] pcture) {
        this.pcture = pcture;
    }

    public String getInterpritation() {
        return interpritation;
    }

    public void setInterpritation(String interpritation) {
        this.interpritation = interpritation;
    }
}

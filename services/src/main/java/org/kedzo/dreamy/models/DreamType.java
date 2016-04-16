package org.kedzo.dreamy.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.print.attribute.standard.MediaSize;

/**
 * Created by woodman on 16.04.16.
 */
public class DreamType implements RepositoryEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private long name;
    @Column(name = "mass")
    private int mass;
    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public long getName() {
        return name;
    }

    public void setName(long name) {
        this.name = name;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }
}

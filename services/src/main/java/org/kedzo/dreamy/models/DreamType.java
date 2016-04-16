package org.kedzo.dreamy.models;

import javax.persistence.*;

@Entity
@Table(name = "dream_type")
public class DreamType {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private long name;
    @Column(name = "weight")
    private int weight;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

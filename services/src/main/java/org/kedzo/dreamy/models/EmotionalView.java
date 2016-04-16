package org.kedzo.dreamy.models;

import javax.persistence.*;

/**
 * Created by woodman on 16.04.16.
 */
@Entity
@Table(name = "emotional_view")
public class EmotionalView {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "common_t")
    private double common;
    @Column(name = "happy_t")
    private double happy;
    @Column(name = "brightly_t")
    private double brightly;
    @Column(name = "friendly_t")
    private double friendly;
    @Column(name = "unstable_t")
    private double unstable;
    @Column(name = "originally_t")
    private double originally;

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public double getCommon() {
        return common;
    }

    public void setCommon(double common) {
        this.common = common;
    }

    public double getHappy() {
        return happy;
    }

    public void setHappy(double happy) {
        this.happy = happy;
    }

    public double getBrightly() {
        return brightly;
    }

    public void setBrightly(double brightly) {
        this.brightly = brightly;
    }

    public double getFriendly() {
        return friendly;
    }

    public void setFriendly(double friendly) {
        this.friendly = friendly;
    }

    public double getUnstable() {
        return unstable;
    }

    public void setUnstable(double unstable) {
        this.unstable = unstable;
    }

    public double getOriginally() {
        return originally;
    }

    public void setOriginally(double originally) {
        this.originally = originally;
    }
}

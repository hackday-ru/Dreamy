package org.kedzo.dreamy.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by woodman on 16.04.16.
 */
@Entity
@Table(name = "users")
public class User implements RepositoryEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "login")
    private String login;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "username")
    private String username;
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "picture_url")
    private String picture;
    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "latitude")
    private float lat;
    @Column(name = "longitude")
    private float lng;
    @Column(name = "is_private")
    private Boolean individual;
    @OneToOne(fetch = FetchType.LAZY)
    private Blog blog;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Dream> dreams;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<User> friends;
    @OneToOne(cascade = CascadeType.ALL)
    private EmotionalView emotionalViews;


    public long getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public Boolean getIndividual() {
        return individual;
    }

    public void setIndividual(Boolean individual) {
        this.individual = individual;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Set<Dream> getDreams() {
        return dreams;
    }

    public void setDreams(Set<Dream> dreams) {
        this.dreams = dreams;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public EmotionalView getEmotionalViews() {
        return emotionalViews;
    }

    public void setEmotionalViews(EmotionalView emotionalViews) {
        this.emotionalViews = emotionalViews;
    }
}


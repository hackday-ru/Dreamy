package org.kedzo.dreamy.models;

/**
 * Created by woodman on 16.04.16.
 */
public class Episode implements RepositoryEntity {
    private long id;
    private byte[] picture;
    private String note;

    public long getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

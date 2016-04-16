package org.kedzo.dreamy.models;

/**
 * Created by woodman on 16.04.16.
 */
public class Tag implements RepositoryEntity {
    private long id;
    private byte[] pcture;
    private String interpritation;

    public long getId() {
        return id;
    }

    public void setId(int id) {
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

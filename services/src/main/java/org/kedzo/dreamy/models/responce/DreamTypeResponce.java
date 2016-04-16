package org.kedzo.dreamy.models.responce;

import org.kedzo.dreamy.models.DreamType;

/**
 * Created by woodman on 17.04.16.
 */
public class DreamTypeResponce {
    private DreamType dreamType;
    private int frequency;

    public DreamTypeResponce(DreamType dreamType, int frequency) {
        this.dreamType = dreamType;
        this.frequency = frequency;
    }

    public DreamType getDreamType() {
        return dreamType;
    }

    public void setDreamType(DreamType dreamType) {
        this.dreamType = dreamType;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}

package org.kedzo.dreamy.models.responce;

import org.kedzo.dreamy.models.Tag;

/**
 * Created by woodman on 17.04.16.
 */
public class PopularTagResponce {
    Integer popular;
    Tag tag;

    public PopularTagResponce(Integer popular, Tag tag) {
        this.popular = popular;
        this.tag = tag;
    }

    public Integer getPopular() {
        return popular;
    }

    public void setPopular(Integer popular) {
        this.popular = popular;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}

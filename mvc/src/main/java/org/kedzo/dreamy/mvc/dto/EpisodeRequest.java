package org.kedzo.dreamy.mvc.dto;

import java.util.List;

public class EpisodeRequest {
    private List<String> tags;
    private Integer order;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}

package com.pam.pam_front.model;

import java.util.List;

public class Genres {
    List<Tag> tags;

    @Override
    public String toString() {
        return "Genres [tags=" + tags + "]";
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}

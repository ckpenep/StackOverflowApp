package com.example.ckpenep.stackoverflow.model.tag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tag {
    private Boolean hasSynonyms;
    private Boolean isModeratorOnly;
    private Boolean isRequired;
    private Integer count;
    private String name;

    public Tag(Boolean hasSynonyms, Boolean isModeratorOnly, Boolean isRequired, Integer count, String name) {
        this.hasSynonyms = hasSynonyms;
        this.isModeratorOnly = isModeratorOnly;
        this.isRequired = isRequired;
        this.count = count;
        this.name = name;
    }

    public Boolean getHasSynonyms() {
        return hasSynonyms;
    }

    public Boolean getModeratorOnly() {
        return isModeratorOnly;
    }

    public Boolean getRequired() {
        return isRequired;
    }

    public Integer getCount() {
        return count;
    }

    public String getName() {
        return name;
    }
}

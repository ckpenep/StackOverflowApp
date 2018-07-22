package com.example.ckpenep.stackoverflow.model.tag;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Tag{" +
                ", count=" + count +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(count, tag.count) &&
                Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(count, name);
    }
}

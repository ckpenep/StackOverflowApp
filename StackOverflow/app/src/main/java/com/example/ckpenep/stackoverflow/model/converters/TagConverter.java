package com.example.ckpenep.stackoverflow.model.converters;

import android.arch.persistence.room.TypeConverter;

import com.example.ckpenep.stackoverflow.model.Question;

import java.util.Arrays;
import java.util.List;

public class TagConverter {
    @TypeConverter
    public List<String> storedStringToTags(String value) {
        List<String> tags = Arrays.asList(value.split("\\s*,\\s*"));
        return tags;
    }

    @TypeConverter
    public String tagsToStoredString(List<String> tags) {
        String value = "";

        for (String tag : tags)
            value += tag + ",";

        return value;
    }
}

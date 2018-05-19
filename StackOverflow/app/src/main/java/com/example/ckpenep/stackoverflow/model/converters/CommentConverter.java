package com.example.ckpenep.stackoverflow.model.converters;

import android.arch.persistence.room.TypeConverter;

import com.example.ckpenep.stackoverflow.model.question.Comment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CommentConverter {
    @TypeConverter
    public List<Comment> storedStringToOwner(String value) {

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Comment>>(){}.getType();
        List<Comment> comments = gson.fromJson(value, listType);

        return comments;
    }

    @TypeConverter
    public String ownerToStoredString(List<Comment> comments) {
        String value = "";

        Gson gson = new Gson();
        value = gson.toJson(comments);

        return value;
    }
}

package com.example.ckpenep.stackoverflow.model.converters;

import android.arch.persistence.room.TypeConverter;

import com.example.ckpenep.stackoverflow.model.question.Answer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AnswerConverter {
    @TypeConverter
    public List<Answer> storedStringToOwner(String value) {

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Answer>>(){}.getType();
        List<Answer> comments = gson.fromJson(value, listType);

        return comments;
    }

    @TypeConverter
    public String ownerToStoredString(List<Answer> comments) {
        String value = "";

        Gson gson = new Gson();
        value = gson.toJson(comments);

        return value;
    }
}

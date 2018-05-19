package com.example.ckpenep.stackoverflow.model.converters;

import android.arch.persistence.room.TypeConverter;

import com.example.ckpenep.stackoverflow.model.question.Owner;
import com.google.gson.Gson;

public class OwnerConverter {
    @TypeConverter
    public Owner storedStringToOwner(String value) {

        Gson gson = new Gson();
        Owner owner = gson.fromJson(value, Owner.class);

        return owner;
    }

    @TypeConverter
    public String ownerToStoredString(Owner owner) {
        String value = "";

        Gson gson = new Gson();
        value = gson.toJson(owner);

        return value;
    }
}

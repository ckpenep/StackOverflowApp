package com.example.ckpenep.stackoverflow.model.system;

import android.content.Context;

public class ResourceManager {

    private Context context;

    public ResourceManager(Context context) {
        this.context = context;
    }

    public String getString(int id)
    {
        return context.getString(id);
    }
}

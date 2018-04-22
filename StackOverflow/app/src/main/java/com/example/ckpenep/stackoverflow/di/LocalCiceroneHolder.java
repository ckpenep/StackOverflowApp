package com.example.ckpenep.stackoverflow.di;

import android.util.SparseArray;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Router;

public class LocalCiceroneHolder {
    private SparseArray<Cicerone<Router>> containers;

    public LocalCiceroneHolder() {
        containers = new SparseArray<>();
    }

    public Cicerone<Router> getCicerone(Integer containerTag) {
        if (containers.indexOfKey(containerTag) < 0) {
            containers.put(containerTag, Cicerone.create());
        }
        return containers.get(containerTag);
    }
}

package com.example.ckpenep.stackoverflow.app;

import android.app.Application;

import com.example.ckpenep.stackoverflow.di.AppComponent;
import com.example.ckpenep.stackoverflow.di.DaggerAppComponent;
import com.example.ckpenep.stackoverflow.di.modules.ContextModule;

public class App extends Application {

    private static AppComponent sAppComponent;

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sAppComponent = DaggerAppComponent
                .builder()
                .contextModule(new ContextModule(this))
                .build();
    }
}
package com.example.ckpenep.stackoverflow.app;

import android.app.Application;

import com.example.ckpenep.stackoverflow.di.AppComponent;
import com.example.ckpenep.stackoverflow.di.DaggerAppComponent;
import com.example.ckpenep.stackoverflow.di.modules.ContextModule;
import com.squareup.leakcanary.LeakCanary;

public class App extends Application {

    private static AppComponent sAppComponent;

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        sAppComponent = DaggerAppComponent
                .builder()
                .contextModule(new ContextModule(this))
                .build();
    }
}
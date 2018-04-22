package com.example.ckpenep.stackoverflow.di.modules;

import com.example.ckpenep.stackoverflow.di.LocalCiceroneHolder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalNavigationModule {

    @Provides
    @Singleton
    LocalCiceroneHolder provideLocalNavigationHolder() {
        return new LocalCiceroneHolder();
    }
}

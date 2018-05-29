package com.example.ckpenep.stackoverflow.di.modules;

import com.example.ckpenep.stackoverflow.ui.adapters.factories.ViewHolderDetailsFactory;
import com.example.ckpenep.stackoverflow.ui.adapters.factories.ViewHolderHistoryFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FabricModule {
    @Singleton
    @Provides
    public ViewHolderDetailsFactory provideDetailsFactory() {
        return new ViewHolderDetailsFactory();
    }

    @Singleton
    @Provides
    public ViewHolderHistoryFactory provideHistoryFactor() {
        return new ViewHolderHistoryFactory();
    }
}

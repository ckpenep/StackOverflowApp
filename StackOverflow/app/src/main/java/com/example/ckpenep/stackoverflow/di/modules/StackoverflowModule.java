package com.example.ckpenep.stackoverflow.di.modules;

import com.example.ckpenep.stackoverflow.app.Api;
import com.example.ckpenep.stackoverflow.model.StackoverflowService;
import com.example.ckpenep.stackoverflow.model.interactor.tag.TagInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ApiModule.class})
public class StackoverflowModule {
    @Provides
    @Singleton
    public StackoverflowService provideGithubService(Api api) {
        return new StackoverflowService(api);
    }

    @Provides
    @Singleton
    public TagInteractor provideTagInteractor() {
        return new TagInteractor();
    }
}

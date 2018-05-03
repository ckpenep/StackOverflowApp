package com.example.ckpenep.stackoverflow.di;

import android.content.Context;

import com.example.ckpenep.stackoverflow.di.modules.ContextModule;
import com.example.ckpenep.stackoverflow.di.modules.LocalNavigationModule;
import com.example.ckpenep.stackoverflow.di.modules.NavigationModule;
import com.example.ckpenep.stackoverflow.di.modules.StackoverflowModule;
import com.example.ckpenep.stackoverflow.presentation.presenter.MainPresenter;
import com.example.ckpenep.stackoverflow.presentation.presenter.QuestionDetailsPresenter;
import com.example.ckpenep.stackoverflow.presentation.presenter.QuestionPresenter;
import com.example.ckpenep.stackoverflow.ui.activity.MainActivity;
import com.example.ckpenep.stackoverflow.ui.fragment.container.ContainerFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ContextModule.class,
        NavigationModule.class,
        LocalNavigationModule.class,
        StackoverflowModule.class
})
public interface AppComponent {

    Context getContext();

    void inject (MainActivity mainActivity);

    void inject (MainPresenter mainPresenter);

    void inject (ContainerFragment containerFragment);

    void inject (QuestionPresenter questionPresenter);

    void inject (QuestionDetailsPresenter questionDetailsPresenter);
}

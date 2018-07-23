package com.example.ckpenep.stackoverflow.di;

import android.content.Context;

import com.example.ckpenep.stackoverflow.di.modules.ContextModule;
import com.example.ckpenep.stackoverflow.di.modules.FabricModule;
import com.example.ckpenep.stackoverflow.di.modules.LocalNavigationModule;
import com.example.ckpenep.stackoverflow.di.modules.NavigationModule;
import com.example.ckpenep.stackoverflow.di.modules.StackoverflowModule;
import com.example.ckpenep.stackoverflow.error.handler.DefaultErrorHandler;
import com.example.ckpenep.stackoverflow.model.interactor.tag.TagInteractor;
import com.example.ckpenep.stackoverflow.presentation.presenter.HistoryPresenter;
import com.example.ckpenep.stackoverflow.presentation.presenter.MainPresenter;
import com.example.ckpenep.stackoverflow.presentation.presenter.QuestionDetailsPresenter;
import com.example.ckpenep.stackoverflow.presentation.presenter.QuestionPresenter;
import com.example.ckpenep.stackoverflow.presentation.presenter.TagsPresenter;
import com.example.ckpenep.stackoverflow.ui.activity.AskActivity;
import com.example.ckpenep.stackoverflow.ui.activity.MainActivity;
import com.example.ckpenep.stackoverflow.ui.adapters.HistoryQuestionsAdapter;
import com.example.ckpenep.stackoverflow.ui.adapters.QuestionDetailsAdapter;
import com.example.ckpenep.stackoverflow.ui.fragment.AskFragment;
import com.example.ckpenep.stackoverflow.ui.fragment.QuestionsListFragment;
import com.example.ckpenep.stackoverflow.ui.fragment.TagsFragment;
import com.example.ckpenep.stackoverflow.ui.fragment.container.ContainerFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ContextModule.class,
        NavigationModule.class,
        LocalNavigationModule.class,
        StackoverflowModule.class,
        FabricModule.class
})
public interface AppComponent {

    Context getContext();

    void inject (DefaultErrorHandler errorHandler);

    void inject (MainActivity mainActivity);

    void inject (MainPresenter mainPresenter);

    void inject (AskActivity askActivity);

    void inject (ContainerFragment containerFragment);

    void inject (QuestionsListFragment questionsListFragment);

    void inject (QuestionPresenter questionPresenter);

    void inject (QuestionDetailsPresenter questionDetailsPresenter);

    void inject (HistoryPresenter historyPresenter);

    void inject (QuestionDetailsAdapter questionDetailsAdapter);

    void inject (HistoryQuestionsAdapter historyQuestionsAdapter);

    void inject (AskFragment askFragment);

    void inject (TagsFragment tagsFragment);

    void inject (TagsPresenter tagsPresenter);

    void inject (TagInteractor tagInteractor);
}

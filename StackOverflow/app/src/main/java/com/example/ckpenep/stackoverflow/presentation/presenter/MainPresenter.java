package com.example.ckpenep.stackoverflow.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.presentation.view.MainView;
import com.example.ckpenep.stackoverflow.ui.Screens;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    Router router;

    public MainPresenter() {
        App.getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    public void onBackPressed() {
        router.exit();
    }

    public void showDialog() {
        getViewState().showDialog();
    }

    public void onTabQuestionClick() {
        router.replaceScreen(Screens.QUESTIONS_SCREEN);
    }

    public void onTabInboxClick() {
        router.replaceScreen(Screens.INBOX_SCREEN);
    }

    public void onTabAchievementClick() {
        router.replaceScreen(Screens.ACHIEVEMENTS_SCREEN);
    }

    public void onTabMoreClick() {router.replaceScreen(Screens.MORE_SCREEN);}

    public void onTabSettingsClick()
    {
        router.navigateTo(Screens.SETTINGS_ACTIVITY_SCREEN);
    }
}

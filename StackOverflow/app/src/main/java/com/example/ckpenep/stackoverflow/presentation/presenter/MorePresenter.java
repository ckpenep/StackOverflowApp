package com.example.ckpenep.stackoverflow.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ckpenep.stackoverflow.presentation.view.MoreView;
import com.example.ckpenep.stackoverflow.ui.Screens;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class MorePresenter extends MvpPresenter<MoreView> {

    private Router router;

    public MorePresenter(Router router) {
        this.router = router;
    }

    public void onBackPressed() {
        router.exit();
    }

    public void clickHistoryButton()
    {
        router.navigateTo(Screens.HISTORY_SCREEN);
    }
}

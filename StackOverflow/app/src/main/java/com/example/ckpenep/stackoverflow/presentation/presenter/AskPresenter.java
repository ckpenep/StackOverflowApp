package com.example.ckpenep.stackoverflow.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ckpenep.stackoverflow.presentation.view.AskView;
import com.example.ckpenep.stackoverflow.ui.Screens;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class AskPresenter extends MvpPresenter<AskView> {

    private Router router;

    public AskPresenter(Router router) {
        this.router = router;
    }

    public void onBackPressed() {
        router.exit();
    }

    public void clickTagsButton()
    {
        router.navigateTo(Screens.TAGS_SCREEN);
    }

    public void textChanged(String title, String body)
    {
        if(!title.isEmpty() && !body.isEmpty())
        {
            getViewState().setMenuVisibility(true);
        }
        else
        {
            getViewState().setMenuVisibility(false);
        }
    }
}

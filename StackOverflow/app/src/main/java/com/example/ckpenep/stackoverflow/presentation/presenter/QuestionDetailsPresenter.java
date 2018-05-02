package com.example.ckpenep.stackoverflow.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ckpenep.stackoverflow.presentation.view.QuestionDetailsView;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class QuestionDetailsPresenter extends MvpPresenter<QuestionDetailsView> {

    private Router router;

    public QuestionDetailsPresenter(Router router) {
        this.router = router;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    public void onBackPressed() {
        router.exit();
    }
}

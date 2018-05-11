package com.example.ckpenep.stackoverflow.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ckpenep.stackoverflow.presentation.view.SignInView;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class SignInPresenter extends MvpPresenter<SignInView> {

    private Router router;

    public SignInPresenter(Router router) {
        this.router = router;
    }

    public void onBackPressed() {
        router.exit();
    }

}

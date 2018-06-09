package com.example.ckpenep.stackoverflow.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ckpenep.stackoverflow.presentation.view.TagsView;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class TagsPresenter extends MvpPresenter<TagsView> {

    private Router router;

    public TagsPresenter(Router router) {
        this.router = router;
    }

    public void onBackPressed() {
        router.exit();
    }
}

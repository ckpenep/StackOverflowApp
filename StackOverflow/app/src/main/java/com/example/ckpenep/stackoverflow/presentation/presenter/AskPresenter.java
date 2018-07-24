package com.example.ckpenep.stackoverflow.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.model.interactor.tag.TagInteractor;
import com.example.ckpenep.stackoverflow.presentation.view.AskView;
import com.example.ckpenep.stackoverflow.ui.Screens;

import java.util.List;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class AskPresenter extends MvpPresenter<AskView> {

    @Inject
    TagInteractor tagInteractor;

    private Router router;
    private boolean isShowDialog;

    public AskPresenter(Router router) {
        App.getAppComponent().inject(this);
        this.router = router;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        if (tagInteractor.getSelectedTags().size() != 0) getViewState().showQuestionDialog();
        else isShowDialog();
    }

    @Override
    public void attachView(AskView view) {
        super.attachView(view);
        getSelectedTags();
    }

    public void onBackPressed() {
        router.exit();
    }

    public void clickNewPostButton() {
        tagInteractor.clearSelectedChips();
        tagInteractor.clearSelectedTags();
        isShowDialog();
    }

    public void isShowDialog() {
        isShowDialog = true;
        getViewState().hideQuestionDialog();
    }

    public void clickTagsButton() {
        router.navigateTo(Screens.TAGS_SCREEN);
    }

    public void textChanged(String title, String body) {
        if (!title.isEmpty() && !body.isEmpty()) {
            getViewState().setMenuVisibility(true);
        } else {
            getViewState().setMenuVisibility(false);
        }
    }

    public void getSelectedTags() {
        List<String> selectedTags = tagInteractor.getSelectedChips();

        String tags = "";
        for (int i = 0; i < selectedTags.size(); i++) {
            if (i != 0) tags += ", ";
            tags += selectedTags.get(i);
        }

        if (isShowDialog) getViewState().setSelectedTags(tags);
    }
}

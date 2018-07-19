package com.example.ckpenep.stackoverflow.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ckpenep.stackoverflow.app.Api;
import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.common.Utils;
import com.example.ckpenep.stackoverflow.error.handler.ErrorHandler;
import com.example.ckpenep.stackoverflow.model.StackoverflowService;
import com.example.ckpenep.stackoverflow.model.dto.questions.QuestionItem;
import com.example.ckpenep.stackoverflow.model.dto.questions.QuestionsList;
import com.example.ckpenep.stackoverflow.model.dto.tag.TagItem;
import com.example.ckpenep.stackoverflow.model.dto.tag.TagsList;
import com.example.ckpenep.stackoverflow.model.question.Question;
import com.example.ckpenep.stackoverflow.model.tag.Tag;
import com.example.ckpenep.stackoverflow.presentation.mappers.QuestionMapper;
import com.example.ckpenep.stackoverflow.presentation.mappers.TagMapper;
import com.example.ckpenep.stackoverflow.presentation.view.QuestionView;
import com.example.ckpenep.stackoverflow.presentation.view.TagsView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class TagsPresenter extends MvpPresenter<TagsView> {

    @Inject
    StackoverflowService mStackoverflowService;
    @Inject
    ErrorHandler mErrorHandler;

    private Router router;
    private Disposable subscription = Disposables.empty();

    private boolean mIsInLoading;

    public TagsPresenter(Router router) {
        App.getAppComponent().inject(this);
        this.router = router;
    }

    @Override
    public void onDestroy() {
        if (!subscription.isDisposed()) {
            subscription.dispose();
        }
    }

    @Override
    public void attachView(TagsView view) {
        super.attachView(view);
        mErrorHandler.attachView(view);
    }

    @Override
    public void detachView(TagsView view) {
        super.detachView(view);
        mErrorHandler.detachView();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadTags();
    }

    private void loadTags() {
        if (mIsInLoading) {
            return;
        }

        mIsInLoading = true;

        final Observable<TagsList> observable = mStackoverflowService.getTagList();

        if (!subscription.isDisposed()) {
            subscription.dispose();
        }

        subscription = observable
                .compose(Utils.applySchedulers())
                .subscribe(questions -> {
                    onLoadingFinish();
                    onLoadingSuccess(questions);
                }, error -> {
                    onLoadingFinish();
                    onLoadingFailed(error);
                });
    }

    private void onLoadingFinish() {
        mIsInLoading = false;
        getViewState().hideProgressDialog();
    }

    private void onLoadingSuccess(TagsList tags) {

        List<TagItem> resultsItems = tags.getItems();
        List<Tag> showingsList = TagMapper.fromResultsItemToTasks(resultsItems);

        getViewState().setTags(showingsList);
    }

    private void onLoadingFailed(Throwable error) {
        getViewState().hideListProgress();
        //getViewState().showError(error.toString());
        mErrorHandler.handleError(error);
    }

    public void onBackPressed() {
        router.exit();
    }
}

package com.example.ckpenep.stackoverflow.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ckpenep.stackoverflow.app.Api;
import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.common.Utils;
import com.example.ckpenep.stackoverflow.model.question.Question;
import com.example.ckpenep.stackoverflow.model.StackoverflowService;
import com.example.ckpenep.stackoverflow.model.dto.questions.QuestionItem;
import com.example.ckpenep.stackoverflow.model.dto.questions.QuestionsList;
import com.example.ckpenep.stackoverflow.presentation.mappers.QuestionMapper;
import com.example.ckpenep.stackoverflow.presentation.view.QuestionView;
import com.example.ckpenep.stackoverflow.ui.Screens;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class QuestionPresenter extends MvpPresenter<QuestionView> {

    private Router router;

    @Inject
    StackoverflowService mStackoverflowService;

    private Disposable subscription = Disposables.empty();

    private boolean mIsInLoading;

    public QuestionPresenter(Router router) {
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
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadRepositories(1, null, "activity", false);
    }

    public void loadNextRepositories(int currentCount, String tagged, String sort) {
        int page = currentCount / Api.PAGE_SIZE + 1;

        loadData(page, tagged, sort, true, false);
    }

    public void loadRepositories(int page, String tagged, String sort, boolean isRefreshing) {
        loadData(page, tagged, sort, false, isRefreshing);
    }

    private void loadData(int page, String tagged, String sort, boolean isPageLoading, boolean isRefreshing) {
        if (mIsInLoading) {
            return;
        }

        mIsInLoading = true;

        getViewState().disableLastItemViewListener();
        showProgress(isPageLoading, isRefreshing);

        Map<String, String> params = new HashMap<String, String>();
        params.put("page", Integer.toString(page));
        params.put("pagesize", Integer.toString(Api.PAGE_SIZE));
        params.put("sort", sort);
        if(tagged != null && !tagged.isEmpty())
        {
            params.put("tagged", tagged);
        }

        final Observable<QuestionsList> observable = mStackoverflowService.getAllQuestions(params);

        if (!subscription.isDisposed()) {
            subscription.dispose();
        }

        subscription = observable
                .compose(Utils.applySchedulers())
                .subscribe(questions -> {
                    onLoadingFinish(isPageLoading, isRefreshing);
                    onLoadingSuccess(isRefreshing, questions);
                }, error -> {
                    onLoadingFinish(isPageLoading, isRefreshing);
                    onLoadingFailed(error);
                });
    }

    private void onLoadingFinish(boolean isPageLoading, boolean isRefreshing) {
        mIsInLoading = false;
        hideProgress(isPageLoading, isRefreshing);
    }

    private void onLoadingSuccess(boolean isRefreshing, QuestionsList questions) {

        List<QuestionItem> resultsItems = questions.getItems();
        List<Question> showingsList = QuestionMapper.fromResultsItemToTasks(resultsItems);

        boolean maybeMore = showingsList.size() >= Api.PAGE_SIZE;
        if (isRefreshing) {
            getViewState().refreshRepositories(showingsList, maybeMore);
        } else {
            getViewState().addRepositories(showingsList, maybeMore);
        }

        getViewState().activateLastItemViewListener();
    }

    private void onLoadingFailed(Throwable error) {
        getViewState().hideProgressBar();
        getViewState().showError(error.toString());
    }

    public void onErrorCancel() {
        getViewState().hideError();
    }

    public void clickItem(Question question) {
        router.navigateTo(Screens.QUESTIONS_DETAILS_SCREEN, question);
    }

    private void showProgress(boolean isPageLoading, boolean isRefreshing) {
        if (isPageLoading) {
            return;
        }

        if (isRefreshing) {
            getViewState().hideProgressBar();
            getViewState().showRefreshing();
        } else {
            getViewState().showListProgress();
        }
    }

    private void hideProgress(boolean isPageLoading, boolean isRefreshing) {
        if (isPageLoading) {
            return;
        }

        if (isRefreshing) {
            getViewState().hideRefreshing();
        } else {
            getViewState().hideListProgress();
        }
    }

    public void hideFloatingActionButton()
    {
        getViewState().hideFloatingActionButton();
    }

    public void showFloatingActionButton()
    {
        getViewState().showFloatingActionButton();
    }
}

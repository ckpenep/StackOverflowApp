package com.example.ckpenep.stackoverflow.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.db.DataDao;
import com.example.ckpenep.stackoverflow.model.Question;
import com.example.ckpenep.stackoverflow.presentation.view.HistoryView;
import com.example.ckpenep.stackoverflow.ui.Screens;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class HistoryPresenter extends MvpPresenter<HistoryView> {

    private Router router;

    @Inject
    DataDao mDataDao;

    private Disposable subscription = Disposables.empty();
    private boolean mIsInLoading;

    public HistoryPresenter(Router router) {
        App.getAppComponent().inject(this);
        this.router = router;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadHistory();
    }

    @Override
    public void onDestroy() {
        if (!subscription.isDisposed()) {
            subscription.dispose();
        }
    }

    private void loadHistory() {
        try {
            if (mIsInLoading) {
                return;
            }

            mIsInLoading = true;

            if (!subscription.isDisposed()) {
                subscription.dispose();
            }

            mDataDao.getAllData().subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<Question>>() {
                        @Override
                        public void accept(List<Question> questions) throws Exception {
                            onLoadingSuccess(questions);
                            onLoadingFinish();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            onLoadingFailed(throwable.getMessage());
                            onLoadingFinish();
                        }
                    });
        } catch (Exception ex) {
            Log.d("DB EXCEPTION", ex.getMessage());
        }
    }

    private void onLoadingFinish() {
        mIsInLoading = false;
        getViewState().hideProgressBar();
    }

    private void onLoadingSuccess(List<Question> questions) {
        getViewState().showResultsItemList(questions);
    }

    private void onLoadingFailed(String error) {
        getViewState().hideProgressBar();
        getViewState().showError(error);
    }

    public void clickItem(Question question) {
        router.navigateTo(Screens.QUESTIONS_DETAILS_SCREEN, question);
    }

    public void onBackPressed() {
        router.exit();
    }
}

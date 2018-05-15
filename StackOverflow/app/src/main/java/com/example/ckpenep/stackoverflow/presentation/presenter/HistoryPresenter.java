package com.example.ckpenep.stackoverflow.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.db.DataDao;
import com.example.ckpenep.stackoverflow.model.Question;
import com.example.ckpenep.stackoverflow.model.dto.history.QuestionDate;
import com.example.ckpenep.stackoverflow.presentation.view.HistoryView;
import com.example.ckpenep.stackoverflow.ui.Screens;
import com.example.ckpenep.stackoverflow.ui.adapters.factories.HistoryRowType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Consumer;
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

            mDataDao.getAllQuestions()
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(unsortedList ->
                    {
                       return sortedList(unsortedList);
                    })
                    .subscribe(new Consumer<List<HistoryRowType>>() {
                        @Override
                        public void accept(List<HistoryRowType> questions) throws Exception {
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

    private List<HistoryRowType> sortedList(List<Question> unsortedList)
    {
        Comparator<Question> comparator = (left, right) -> (int)(left.getSaveDate() - right.getSaveDate());
        Collections.sort(unsortedList, comparator);

        List<HistoryRowType> separatedList = new ArrayList<>();
        Long date = 0L;
        int separateCount =0;
        for (int i=0; i < unsortedList.size(); i++)
        {
            Log.d("DATES", "Date: " + date + "   Qdate: " + unsortedList.get(i).getSaveDate());
            if(!date.equals(unsortedList.get(i).getSaveDate()))
            {
                separatedList.add(i + separateCount, new QuestionDate(unsortedList.get(i).getSaveDate()));
                separateCount++;
                date = unsortedList.get(i).getSaveDate();
            }
            separatedList.add(i + separateCount, unsortedList.get(i));
        }
        return separatedList;
    }

    private void onLoadingFinish() {
        mIsInLoading = false;
        getViewState().hideProgressBar();
    }

    private void onLoadingSuccess(List<HistoryRowType> questions) {
        Log.d("SEPARATED", questions.toString());
        getViewState().showResultsItemList(questions);
    }

    private void onLoadingFailed(String error) {
        getViewState().hideProgressBar();
        getViewState().showError(error);
    }

    public void clickItem(HistoryRowType question) {
        if(question instanceof Question) router.navigateTo(Screens.QUESTIONS_DETAILS_SCREEN, (Question)question);
    }

    public void onBackPressed() {
        router.exit();
    }
}

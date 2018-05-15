package com.example.ckpenep.stackoverflow.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.common.Utils;
import com.example.ckpenep.stackoverflow.db.DataDao;
import com.example.ckpenep.stackoverflow.model.Question;
import com.example.ckpenep.stackoverflow.model.StackoverflowService;
import com.example.ckpenep.stackoverflow.presentation.mappers.DetailsMapper;
import com.example.ckpenep.stackoverflow.presentation.view.QuestionDetailsView;
import com.example.ckpenep.stackoverflow.ui.adapters.factories.DetailsRowType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class QuestionDetailsPresenter extends MvpPresenter<QuestionDetailsView> {

    @Inject
    DataDao mDataDao;
    @Inject
    DetailsMapper mDetailsMapper;
    @Inject
    StackoverflowService mStackoverflowService;

    private boolean mIsInLoading;
    private Question mQuestion;
    private Router router;
    private final List<DetailsRowType> mResultsItems = new ArrayList<>();

    private Disposable subscription = Disposables.empty();

    public QuestionDetailsPresenter(Router router, Question question) {
        App.getAppComponent().inject(this);
        this.router = router;
        mQuestion = question;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        saveQuestionToDB(mQuestion);

        loadData(mQuestion.getId());
    }

    private void loadData(Integer id) {

        if (mIsInLoading) {
            return;
        }
        mIsInLoading = true;

        final Observable<DetailsRowType> observable = mStackoverflowService.getAnswersByQuestion(id);
        if (!subscription.isDisposed()) {
            subscription.dispose();
        }
        subscription = observable
                .compose(Utils.applySchedulers())
                //.map(mDetailsMapper)
                .subscribe(resp -> {
                    onLoadingFinish();
                    onLoadingSuccess(resp);
                }, error -> {

                });
    }

    private void onLoadingSuccess(DetailsRowType response) {

    }

    private void onLoadingFinish() {
        mIsInLoading = false;
    }

    public void onBackPressed() {
        router.exit();
    }

    private void saveQuestionToDB(Question question)
    {
        try {
            Calendar cal = Calendar.getInstance();
            int year  = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int date  = cal.get(Calendar.DATE);
            cal.clear();
            cal.set(year, month, date);

            question.setSaveDate(cal.getTimeInMillis());

            mDataDao.insert(question);
        }
        catch (Exception ex)
        {
            Log.e("SAVE ROOM", ex.getMessage());
        }
    }

    @Override
    public void onDestroy() {
        if (!subscription.isDisposed()) {
            subscription.dispose();
        }
    }
}

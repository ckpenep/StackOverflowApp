package com.example.ckpenep.stackoverflow.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.common.Utils;
import com.example.ckpenep.stackoverflow.db.DataDao;
import com.example.ckpenep.stackoverflow.model.StackoverflowService;
import com.example.ckpenep.stackoverflow.model.datails.AnswerDetail;
import com.example.ckpenep.stackoverflow.model.datails.HeaderDetail;
import com.example.ckpenep.stackoverflow.model.datails.QuestionDetail;
import com.example.ckpenep.stackoverflow.model.dto.answer.AnswersList;
import com.example.ckpenep.stackoverflow.model.dto.questions.AnswerItem;
import com.example.ckpenep.stackoverflow.model.question.Question;
import com.example.ckpenep.stackoverflow.presentation.mappers.AnswerDetailMapper;
import com.example.ckpenep.stackoverflow.presentation.mappers.QuestionDetailsMapper;
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
    QuestionDetailsMapper mDetailsMapper;
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

        mResultsItems.add(new HeaderDetail());

        QuestionDetail questionDetail = mDetailsMapper.apply(mQuestion);
        mResultsItems.add(questionDetail);

        getViewState().showResult(mResultsItems);

        if(mQuestion.getAnswerCount() > 0)
        {
            loadData(getAnswersId());
        }
    }

    private void loadData(String ids) {

        if (mIsInLoading) {
            return;
        }
        mIsInLoading = true;

        final Observable<AnswersList> observable = mStackoverflowService.getAnswersByIds(ids);

        if (!subscription.isDisposed()) {
            subscription.dispose();
        }

        subscription = observable
                .compose(Utils.applySchedulers())
                .subscribe(answers -> {
                    onLoadingFinish();
                    onLoadingSuccess(answers);
                }, error -> {
                    onLoadingFinish();
                    onLoadingFailed(error);
                });
    }

    private void onLoadingSuccess(AnswersList response) {
        List<AnswerItem> resultsItems = response.getItems();
        List<AnswerDetail> answersList = AnswerDetailMapper.fromAnswerItemToAnswerDetail(resultsItems);

        mResultsItems.addAll(answersList);
        getViewState().showResult(mResultsItems);
    }

    private void onLoadingFinish() {
        mIsInLoading = false;
    }

    private void onLoadingFailed(Throwable error) {
        Log.d("ERROR", error.getMessage());
        getViewState().showError(error.toString());
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

    private String getAnswersId()
    {
        if(mQuestion != null && mQuestion.getAnswerCount() > 0)
        {
            String ids = "";

            for(int i=0; i < mQuestion.getAnswers().size(); i++)
            {
                if(i != 0) ids += ";";

                ids += mQuestion.getAnswers().get(i).getAnswerId();
            }

            return ids;
        }

        return null;
    }

    @Override
    public void onDestroy() {
        if (!subscription.isDisposed()) {
            subscription.dispose();
        }
    }
}

package com.example.ckpenep.stackoverflow.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.db.DataDao;
import com.example.ckpenep.stackoverflow.model.Question;
import com.example.ckpenep.stackoverflow.presentation.view.QuestionDetailsView;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class QuestionDetailsPresenter extends MvpPresenter<QuestionDetailsView> {

    @Inject
    DataDao mDataDao;

    private Question mQuestion;
    private Router router;

    public QuestionDetailsPresenter(Router router, Question question) {
        App.getAppComponent().inject(this);
        this.router = router;
        mQuestion = question;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        saveQuestionToDB(mQuestion);
    }

    public void onBackPressed() {
        router.exit();
    }

    private void saveQuestionToDB(Question question)
    {
        try {
            mDataDao.insert(question);
        }
        catch (Exception ex)
        {
            Log.e("ROOM", ex.getMessage());
        }
    }
}

package com.example.ckpenep.stackoverflow.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.ckpenep.stackoverflow.model.Question;

import java.util.List;

public interface HistoryView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void showError(String error);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideProgressBar();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showResultsItemList(List<Question> questionsList);
}

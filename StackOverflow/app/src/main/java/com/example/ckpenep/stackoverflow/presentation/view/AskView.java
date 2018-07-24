package com.example.ckpenep.stackoverflow.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface AskView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void setMenuVisibility(boolean flag);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setSelectedTags(String tags);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showQuestionDialog();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideQuestionDialog();
}

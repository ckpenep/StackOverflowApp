package com.example.ckpenep.stackoverflow.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.ckpenep.stackoverflow.ui.adapters.factories.DetailsRowType;

import java.util.List;

public interface QuestionDetailsView extends MvpView {

    @StateStrategyType(AddToEndStrategy.class)
    void showResult(List<DetailsRowType> result);

    @StateStrategyType(SkipStrategy.class)
    void showError(String error);
}

package com.example.ckpenep.stackoverflow.error;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface ErrorOutput extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void showError(String error);
}

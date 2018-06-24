package com.example.ckpenep.stackoverflow.presentation.view;

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.ckpenep.stackoverflow.error.ErrorOutput;
import com.example.ckpenep.stackoverflow.model.question.Question;

import java.util.List;

public interface QuestionView extends ErrorOutput {

    void hideProgressBar();

    void hideError();

    void showRefreshing();

    void hideRefreshing();

    void showListProgress();

    void hideListProgress();

    void activateLastItemViewListener();

    void disableLastItemViewListener();

    void refreshRepositories(List<Question> repositories, boolean maybeMore);

    @StateStrategyType(AddToEndStrategy.class)
    void addRepositories(List<Question> repositories, boolean maybeMore);

    void hideFloatingActionButton();

    void showFloatingActionButton();
}

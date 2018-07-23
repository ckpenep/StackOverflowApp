package com.example.ckpenep.stackoverflow.presentation.view;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.ckpenep.stackoverflow.error.ErrorOutput;
import com.example.ckpenep.stackoverflow.model.tag.Tag;

import java.util.List;

public interface TagsView extends ErrorOutput {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideProgressDialog();

    void showListProgress();

    void hideListProgress();

    @StateStrategyType(AddToEndStrategy.class)
    void setTags(List<Tag> tags);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setChips(List<String> names);
}

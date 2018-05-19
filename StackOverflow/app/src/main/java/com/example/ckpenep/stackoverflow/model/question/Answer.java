package com.example.ckpenep.stackoverflow.model.question;

import android.support.v7.widget.RecyclerView;

import com.example.ckpenep.stackoverflow.ui.adapters.factories.DetailsRowType;
import com.example.ckpenep.stackoverflow.ui.adapters.factories.ViewHolderDetailsFactory;

public class Answer implements DetailsRowType {





    @Override
    public int getItemViewType() {
        return DetailsRowType.ANSWER_ROW_TYPE;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {
        ViewHolderDetailsFactory.AnswerViewHolder historyViewHolder = (ViewHolderDetailsFactory.AnswerViewHolder) viewHolder;


    }
}

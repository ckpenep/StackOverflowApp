package com.example.ckpenep.stackoverflow.ui.adapters.factories;

import android.support.v7.widget.RecyclerView;

public interface DetailsRowType {
    int QUESTION_ROW_TYPE =   0;
    int ANSWER_ROW_TYPE = 1;

    int getItemViewType();

    void onBindViewHolder(RecyclerView.ViewHolder viewHolder);
}
